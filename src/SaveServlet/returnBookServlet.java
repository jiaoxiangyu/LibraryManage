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

@WebServlet(name="returnBookServlet",urlPatterns="/returnBookServlet")//配置文件
public class returnBookServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		String rno = (String) session.getAttribute("RNO");//获得用户学号/工号	，和要还的图书的条形码		
		String returnbarCode = request.getParameter("returnbarCode");			
   	 if (conn != null) {
			try {											
				
				int num=0;
				String selectSql02="select * from reader where rNo=?";
				PreparedStatement ps02 = conn.prepareStatement(selectSql02);				
				ps02.setString(1, rno);
				ResultSet rs02=ps02.executeQuery();	
				while(rs02.next()){	
					num=rs02.getInt("lendNum");//获得用户借阅数量
						
				}
				
				String bookName="";
				String selectSql_1="select * from books where barCode=?"; 
				PreparedStatement ps_1 = conn.prepareStatement(selectSql_1);				
				ps_1.setString(1, returnbarCode);
				ResultSet rs_1=ps_1.executeQuery();	
				while(rs_1.next()){						
					bookName=rs_1.getString("bName");//获得该图书名称
					
				}
				
				
				int booknum=0;//定义该图书的库存的数量
				String selectSql_2="select * from booknum where bName=?"; 
				PreparedStatement ps_2 = conn.prepareStatement(selectSql_2);				
				ps_2.setString(1, bookName);
				ResultSet rs_2=ps_2.executeQuery();	
				while(rs_2.next()){						
					booknum=rs_2.getInt("num");//获得该图书的库存的数量
					
				}
				
				if(num>0){//判断借阅数量是否大于0
					String sql="delete from lendbooks where rNo=? and barCode=?;"; //归还图书
					//创建PreparedStatement对象
					PreparedStatement ps = conn.prepareStatement(sql);												   					
					ps.setString(1, rno);
					ps.setString(2,returnbarCode);
					ps.executeUpdate();
					
					
					String insertSql2="update reader set lendNum=?,canLendNum=? where rNo=?"; 
					//创建PreparedStatement对象
			    	PreparedStatement ps12 = conn.prepareStatement(insertSql2);
			    	int num1=num-1;
					int num2=10-num1;
					ps12.setInt(1, num1);//更改借阅数量和可借数量
					ps12.setInt(2, num2);
					ps12.setString(3, rno);
					ps12.executeUpdate();				
								
					String updateSql="update books set state='未借',borrower='' where barCode=?";//更改图书借阅状态为‘未借’
					PreparedStatement ps2 = conn.prepareStatement(updateSql);
					ps2.setString(1, returnbarCode);
					ps2.executeUpdate();
					
					String updateSql_3="update booknum set num=? where bName=?";//更改图书库存
					PreparedStatement ps_3 = conn.prepareStatement(updateSql_3);
					booknum++;
					ps_3.setInt(1, booknum);
					ps_3.setString(2, bookName);
					ps_3.executeUpdate();
				
					//输出结果信息
					PrintWriter out = response.getWriter();
					out.println("<script language='javascript'>");
					out.println("var str='还图书成功！';");
					out.println("alert(str);");	
					out.println("window.navigate('LendBooks.jsp');");
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
