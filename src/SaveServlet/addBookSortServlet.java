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

@WebServlet(name="addBookSortServlet",urlPatterns="/addBookSortServlet")//注释
public class addBookSortServlet extends HttpServlet {
	private Connection conn=null;
	private static final long serialVersionUID = 1L;
      
	public void init(ServletConfig config) throws ServletException {
		 (new MysqlLinking()).getLink();//连接数据库
    	 conn=MysqlLinking.conn;//得到连接数据库的Connection对象conn	    		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
   	 if (conn != null) {
   		    response.setContentType("text/html");
		    request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
   		    String bt=request.getParameter("bsort");//获得表单属性图书类型
			try {				
				String sql="insert into booksort(bSort) values(?)"; //添加图书类型
				PreparedStatement ps = conn.prepareStatement(sql);	
				ps.setString(1, bt);
				ps.executeUpdate();	
				//获取PrintWriter对象
				PrintWriter out = response.getWriter();
				//输出注册结果信息				
				out.println("<script language='javascript'>");
				out.println("var str='图书类别添加成功！';");
				out.println("alert(str);");	
				out.println("window.navigate('booksort.jsp');");
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
