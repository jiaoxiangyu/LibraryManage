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

import com.lyq.bean.MysqlLinking;

@WebServlet(name = "AddReaderServlet",urlPatterns ="/AddReaderServlet")//注释
public class AddReaderServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;
    private Connection conn=null;
    
	public void init(ServletConfig config) throws ServletException {
		 (new MysqlLinking()).getLink();//连接数据库
    	 conn=MysqlLinking.conn;//得到连接数据库的Connection对象conn	    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取表单中属性值
				response.setContentType("text/html");
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				String rName = request.getParameter("rName");//获取表单属性
				String rsex = request.getParameter("rsex");
				String rage = request.getParameter("rage");
				String rClass = request.getParameter("rClass");
				String rSort = request.getParameter("rSort");
				String rNo = request.getParameter("rNo");
				String rPhoneNum = request.getParameter("rPhoneNum");				
		   	 if (conn != null) {
					try {
						//插入注册信息的SQL语句(使用?占位符)   //添加读者信息						
						String sql="insert into reader(rNo,rName,rsex,rage,rClass,rSort,rPhoneNum,password,lendNum,canLendNum) values(?,?,?,?,?,?,?,?,?,?)"; 
						//创建PreparedStatement对象
						PreparedStatement ps = conn.prepareStatement(sql);
						//对SQL语句中的参数动态赋值
						ps.setString(1,rNo);
					   	ps.setString(2,rName);
					   	ps.setString(3,rsex);
					   	ps.setInt(4,Integer.valueOf(rage));
					   	ps.setString(5,rClass);	   	 
					   	ps.setString(6,rSort);
					   	ps.setString(7,rPhoneNum);	
					   	ps.setString(8,"123456");
					   	ps.setInt(9, 0);
					   	ps.setInt(10, 10);
						//执行更新操作
					   	ps.executeUpdate();					
						//获取PrintWriter对象
						PrintWriter out = response.getWriter();//输出结果信息																								
						out.println("<script language='javascript'>");
						out.println("var str='用户添加成功！默认密码是123456,请登录修改！';");//默认密码是'123456',请登录修改！
						out.println("alert(str);");								
						out.println("window.navigate('AddReader.jsp');");
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
