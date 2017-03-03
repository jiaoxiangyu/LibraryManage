 package SaveServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyq.bean.MysqlLinking;

@WebServlet(name = "LoginServlet",urlPatterns ="/LoginServlet")//注释配置文件
public class LoginServlet extends HttpServlet {
	    private Connection conn=null;
		private static final long serialVersionUID = 1L;
		  
		public void init(ServletConfig config) throws ServletException {
			 (new MysqlLinking()).getLink();//连接数据库
	    	 conn=MysqlLinking.conn;//得到连接数据库的Connection对象conn	    			
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//获取表单中属性值,
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String Usersort = request.getParameter("UserSort");	//获得用户登录类型、用户名、密码
			String username = request.getParameter("username");			
			String pwd = request.getParameter("pwd");			    
			HttpSession session = request.getSession();//创建Session对象
	   	 if (conn!= null) {
				try {							
					String sql="select * from reader where rName=? and password=?"; 
					//创建PreparedStatement对象
					PreparedStatement ps = conn.prepareStatement(sql);				
					ps.setString(1, username);	
					ps.setString(2, pwd);	
					ResultSet rs=ps.executeQuery();	
					String Uname="";
					String Pdw="";
					String usersort="";
					while(rs.next()){							
						Uname=rs.getString("rName");  //从数据库中获得用户名，密码，用户类型
						Pdw=rs.getString("password");
						usersort=rs.getString("rSort");
												
						String RNO=rs.getString("rNo");	//把	用户学号/工号，用户名，用户类型存在Session对象中			
						session.setAttribute("RNO",RNO);
						String NAME=Uname;
						session.setAttribute("NAME",NAME);
						String RSORT=usersort;
						session.setAttribute("RSORT",RSORT);
					}
					if(Uname.equals(username) && Pdw.equals(pwd)){//判断用户名，登录密码是否正确
						if(usersort.equals(Usersort) && usersort.equals("管理员")){//用户类型为管理员时转换到/M_HomePage.jsp页面
							request.getRequestDispatcher("/M_HomePage.jsp").forward(request, response);
						}
						else if(usersort.equals(Usersort) && usersort.equals("学生")){//用户类型为学生时转换到/R_HomePage.jsp页面
							request.getRequestDispatcher("/R_HomePage.jsp").forward(request, response);
						}
						else{//如果用户类别错误
							PrintWriter out = response.getWriter();					
							out.println("<script language='javascript'>");
							out.println("var str='用户类别错误！请重新输入！';");
							out.println("alert(str);");	
							out.println("window.navigate('index.jsp');");
							out.println("</script>");										
							out.flush();
							out.close();						
						}
					}
					else{//用户名或密码错误
						PrintWriter out = response.getWriter();					
						out.println("<script language='javascript'>");
						out.println("var str='用户名或密码错误！请重新输入！';");
						out.println("alert(str);");	
						out.println("window.navigate('index.jsp');");
						out.println("</script>");										
						out.flush();
						out.close();						
					}			
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
