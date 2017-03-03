package SaveServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyq.bean.BookSort;
import com.lyq.bean.MysqlLinking;

@WebServlet(name="BookSortInfoServlet",urlPatterns="/BookSortInfoServlet")//注释
public class BookSortInfoServlet extends HttpServlet {
	private Connection conn=null;
    private static final long serialVersionUID = 1L;  
    public void init(ServletConfig config) throws ServletException {
    	 (new MysqlLinking()).getLink();//连接数据库
    	 conn=MysqlLinking.conn;//得到连接数据库的Connection对象conn	    	    
     }		
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       response.setContentType("text/html");
	       request.setCharacterEncoding("UTF-8");
	       response.setCharacterEncoding("UTF-8");	      	
		   List<BookSort> bslist2=new ArrayList<>();
	   	   if (conn != null) {
				try {							
					String sql="select * from booksort ";//查询所有图书类型 
					//创建PreparedStatement对象
					PreparedStatement ps = conn.prepareStatement(sql);													   	
					ResultSet rs=ps.executeQuery();						
					while(rs.next()){//把所有图书类型存放在list集合中
						BookSort bs=new BookSort();
						bs.setBooksort((rs.getString("bSort")));
						bslist2.add(bs);
					}					
					request.setAttribute("bslist2", bslist2);//储存bslist2集合				
					request.getRequestDispatcher("/booksort.jsp").forward(request, response);//转换页面				                			
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			} 
	   	    else {
				//发送数据库连接错误提示信息
				response.sendError(500, "数据库连接错误！");
			}
     }
}
