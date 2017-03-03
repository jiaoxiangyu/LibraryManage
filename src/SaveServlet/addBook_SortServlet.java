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

@WebServlet(name="addBook_SortServlet",urlPatterns="/addBook_SortServlet")//注释
public class addBook_SortServlet extends HttpServlet {
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
		   List<BookSort> bslist=new ArrayList<>();
	   	   if (conn != null) {
				try {							
					String sql="select * from booksort "; //获得图书类型，在图书类型下拉菜单中输出				
					PreparedStatement ps = conn.prepareStatement(sql);	//创建PreparedStatement对象												   	
					ResultSet rs=ps.executeQuery();						
					while(rs.next()){	
						BookSort bs=new BookSort();
						bs.setBooksort((rs.getString("bSort")));
						bslist.add(bs);//把保存图书类型在bslist集合中
					}
					
					request.setAttribute("bslist", bslist);		//把bslist存放在request对象中		
					request.getRequestDispatcher("/AddBook.jsp").forward(request, response);//转到AddBook.jsp页面				                			
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
