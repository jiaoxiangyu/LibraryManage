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

@WebServlet(name = "removeReaderServlet",urlPatterns ="/removeReaderServlet")//配置文件
public class removeReaderServlet extends HttpServlet {
	private Connection conn=null;	
	private static final long serialVersionUID = 1L;	   
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
		String rmreaderName = request.getParameter("rmreaderName");	//获得要删除用户名和学号/工号
		String rno = request.getParameter("rNo");	
		
   	 if (conn != null) {
			try {							
				String sql="delete from reader where rName=? and rNo=?;";//删除用户 
				//创建PreparedStatement对象
				PreparedStatement ps = conn.prepareStatement(sql);												   					
				ps.setString(1, rmreaderName);
				ps.setString(2,rno);
				ps.executeUpdate();
				PrintWriter out = response.getWriter();
				//输出注册结果信息			
				out.println("<script language='javascript'>");
				out.println("var str='用户删除成功！';");
				out.println("alert(str);");	
				out.println("window.navigate('remveReader.jsp');");
				out.println("</script>");
				out.flush();
				out.close();										               			
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
