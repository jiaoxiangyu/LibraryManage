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

import com.lyq.bean.Book;
import com.lyq.bean.MysqlLinking;

@WebServlet(name= "AllBookInfo" , urlPatterns="/AllBookInfo")//注释
public class AllBookInfo extends HttpServlet {
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
		   List<Book> list=new ArrayList<>();
	   	   if (conn != null) {
				try {							
					String sql="select * from books "; //查询所有图书
					//创建PreparedStatement对象
					PreparedStatement ps = conn.prepareStatement(sql);													   	
					ResultSet rs=ps.executeQuery();	
					
					while(rs.next()){	//遍历数据库内所有图书信息
						Book b=new Book();//创建book对象
						
						String bookname=rs.getString("bName");
						String sql2="select num from booknum where bName=?"; //查询图书库存
						PreparedStatement ps2 = conn.prepareStatement(sql2);
						ps2.setString(1, bookname);
						ResultSet rs2=ps2.executeQuery();
						int booknum=0;
						while(rs2.next()){
							booknum=rs2.getInt("num");
						}
						
						b.setbName(rs.getString("bName"));					
						b.setwriter(rs.getString("writer"));
						b.setpress(rs.getString("press"));
						b.setpresstime(rs.getString("presstime"));
						b.setbarCode(rs.getString("barCode"));
						b.setsort(rs.getString("sort"));
						b.setstate(rs.getString("state"));
						b.setprice(rs.getDouble("price"));
						b.setpageNum(rs.getInt("pageNum"));
						b.setBooknum(booknum);
						//b.setLendNum(rs.getInt("LendNum"));
						list.add(b);//把book对象b放在list集合中
					}
					/*for(int i=0;i<list.size();i++){//统计图书借阅次数
						int numi=list.get(i).getLendNum(),sum=0;
						for(int j=1;j<list.size()-1;j++){							
							if(list.get(i).getbName().equals(list.get(j).getbName())){															
								int numj=list.get(j).getLendNum();
								list.remove(j);
								sum=sum+numj;								
							}						
						}
						list.get(i).setLendNum(numi+sum);	
					}*/
					for(int i=0;i<list.size();i++){//删除重复的图书
						for(int j=0;j<list.size()-1;j++){
							if(list.get(i).getbName().equals(list.get(j).getbName())){
								list.remove(j);
							}
						}
					}
					
					request.setAttribute("list", list);	//把list储存在request对象中			
					request.getRequestDispatcher("/AllBookInfo.jsp").forward(request, response);//转到/AllBookInfo.jsp页面				                			
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

