package SaveServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyq.bean.MysqlLinking;

@WebServlet(name = "AddBookServlet",urlPatterns ="/AddBookServlet")//注释
public class AddBookServlet extends HttpServlet {
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
		String bName = request.getParameter("bName");//获取表单中属性值
		String writer = request.getParameter("writer");
		String press = request.getParameter("press");
		String presstime = request.getParameter("presstime");
		String price = request.getParameter("price");
		String pageNum = request.getParameter("pageNum");
		String sort = request.getParameter("sort");
		String barCode = request.getParameter("barCode");
   	 if (conn != null) {
			try {
				//插入注册信息的SQL语句(使用?占位符)	 //添加图书属性			
				String sql="insert into books(bName,writer,press,presstime,price,pageNum,sort,barCode,LendNum,state,borrower) values(?,?,?,?,?,?,?,?,?,?,?)"; 
				//创建PreparedStatement对象
				PreparedStatement ps = conn.prepareStatement(sql);
				//对SQL语句中的参数动态赋值
				ps.setString(1,bName);
			   	ps.setString(2,writer);
			   	ps.setString(3,press);
			   	ps.setString(4,presstime);
			   	ps.setDouble(5,Double.valueOf(price));			   	 
			   	ps.setInt(6,Integer.valueOf(pageNum));
			   	ps.setString(7,sort);
			   	ps.setString(8,barCode);
			   	ps.setInt(9,0);
			   	ps.setString(10,"未借");
			   	ps.setString(11,"");
				//执行更新操作
			   	 ps.executeUpdate();				
				//获取PrintWriter对象
				PrintWriter out = response.getWriter();
				//输出注册结果信息				
				out.println("<script language='javascript'>");
				out.println("var str='图书添加成功！';");
				out.println("alert(str);");	
				out.println("window.navigate('AddBook.jsp');");
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
