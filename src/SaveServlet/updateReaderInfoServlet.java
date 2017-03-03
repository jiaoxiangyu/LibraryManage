package SaveServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyq.bean.MysqlLinking;

@WebServlet(name="updateReaderInfoServlet",urlPatterns="/updateReaderInfoServlet")//配置文件
public class updateReaderInfoServlet extends HttpServlet {
	private Connection conn=null;
	private static final long serialVersionUID = 1L;
	  
	public void init(ServletConfig config) throws ServletException {
		 (new MysqlLinking()).getLink();//连接数据库
    	 conn=MysqlLinking.conn;//得到连接数据库的Connection对象conn	    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				HttpSession session = request.getSession();
				String rno = (String) session.getAttribute("RNO");//获得用户更改后的信息
				String rName =(String) session.getAttribute("NAME");
				String rsex = request.getParameter("newrsex");
				String rage = request.getParameter("newrage");
				String rClass = request.getParameter("newrClass");						
				String rPhoneNum = request.getParameter("newrPhoneNum");
				String password = request.getParameter("newPassword");
				
		   	 if (conn != null) {
					try {
						//插入注册信息的SQL语句(使用?占位符)						
						String sql="update  reader set rsex=?,rage=?,rClass=?,rPhoneNum=?,password=? where rNo=? and rName=?"; 
						//创建PreparedStatement对象
						PreparedStatement ps = conn.prepareStatement(sql);
						//对SQL语句中的参数动态赋值
						ps.setString(1,rsex);
						ps.setInt(2,Integer.valueOf(rage));
						ps.setString(3,rClass);
						ps.setString(4,rPhoneNum);
						ps.setString(5,password);
						ps.setString(6,rno);
					   	ps.setString(7,rName);					   					   					  
						//执行更新操作
					   	 ps.executeUpdate();					
						//获取PrintWriter对象
						PrintWriter out = response.getWriter();
						//输出注册结果信息						
						out.println("<script language='javascript'>");
						out.println("var str='修改成功！请记好你的密码！';");
						out.println("alert(str);");	
						out.println("window.navigate('updateReaderInfo.jsp');");
						out.println("</script>");
						out.flush();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					//发送数据库连接错误提示信息
					response.sendError(500, "数据库连接错误！");
				}
	}
	
}
