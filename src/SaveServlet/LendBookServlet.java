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

@WebServlet(name = "LendBookServlet",urlPatterns ="/LendBookServlet")//注释
public class LendBookServlet extends HttpServlet {
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
		HttpSession session = request.getSession();//创建Session对象
		String rno = (String) session.getAttribute("RNO");//从Session对象中获得用户学号/工号
		String name = (String) session.getAttribute("NAME");
		String barCode = request.getParameter("barCode");	//获得要借的的图书名
		String nowState="",bookName="";//定义图书借阅状态、书名和借阅数量变量
		int LendNums=0;
   	 if (conn != null) {
			try {
				String selectSql="select * from books where barCode=?"; 
				PreparedStatement ps0 = conn.prepareStatement(selectSql);				
				ps0.setString(1, barCode);
				ResultSet rs=ps0.executeQuery();	
				while(rs.next()){	
					nowState=rs.getString("state");//获得图书借阅状态和借阅数量
					LendNums=rs.getInt("LendNum");
					bookName=rs.getString("bName");
				}
				
				int booknum=0;//定义该图书的库存的数量
				String selectSql_2="select * from booknum where bName=?"; 
				PreparedStatement ps_2 = conn.prepareStatement(selectSql_2);				
				ps_2.setString(1, bookName);
				ResultSet rs_2=ps_2.executeQuery();	
				while(rs_2.next()){						
					booknum=rs_2.getInt("num");//获得该图书的库存的数量
					
				}
				
				int num=0;//定义户借阅数量变量
				if(nowState.equals("未借")){//判断图书是否已被借
					String selectSql02="select * from reader where rNo=?";
					PreparedStatement ps02 = conn.prepareStatement(selectSql02);				
					ps02.setString(1, rno);
					ResultSet rs02=ps02.executeQuery();	
					while(rs02.next()){	
						num=rs02.getInt("lendNum");//获得用户借阅数量
						
					}
					if(num<=10){//判断用户数量是否达到上限
						String insertSql="insert into lendbooks(rNo,borrower,lendbook,barCode) values (?,?,?,?)"; 
						//创建PreparedStatement对象
						PreparedStatement ps1 = conn.prepareStatement(insertSql);												   					
						ps1.setString(1, rno);   //借阅图书
						ps1.setString(2,name);
						ps1.setString(3,bookName);
						ps1.setString(4,barCode);
						ps1.executeUpdate();
																	
						String updateSql2="update reader set lendNum=?,canLendNum=? where rNo=?"; 
						//创建PreparedStatement对象
						PreparedStatement ps12 = conn.prepareStatement(updateSql2);
						int num1=num+1;//图书借阅数量+1，可借数量-1
						int num2=10-num1;
						ps12.setInt(1, num1);
						ps12.setInt(2, num2);
						ps12.setString(3, rno);
						ps12.executeUpdate();
						
						String updateSql="update books set state='已借',LendNum=?,borrower=? where barCode=?";
						PreparedStatement ps2 = conn.prepareStatement(updateSql);
						LendNums++;
						ps2.setInt(1, LendNums);//改变图书借阅状态和借阅数量+1
						ps2.setString(2, name);
						ps2.setString(3,barCode);
						ps2.executeUpdate();
						
						String updateSql_3="update booknum set num=? where bName=?";
						PreparedStatement ps_3 = conn.prepareStatement(updateSql_3);
						booknum=booknum-1;
						ps_3.setInt(1, booknum);//改变图书库存数量-1
						ps_3.setString(2, bookName);
						ps_3.executeUpdate();
												
						PrintWriter out = response.getWriter();//输出结果信息						
						out.println("<script language='javascript'>");
						out.println("var str='图书借阅成功！';");
						out.println("alert(str);");	
						out.println("window.navigate('LendBooks.jsp');");
						out.println("</script>");	
						out.flush();
						out.close();
					}
					else{
						PrintWriter out = response.getWriter();//输出结果信息						
						out.println("<script language='javascript'>");
						out.println("var str='对不起，你的借阅数量已达到上限！';");
						out.println("alert(str);");	
						out.println("window.navigate('LendBooks.jsp');");
						out.println("</script>");
						out.flush();
						out.close();
					}				
				}
				else{
					PrintWriter out = response.getWriter();//输出结果信息					
					out.println("<script language='javascript'>");
					out.println("var str='该图书已被借阅，请等候归还后再借！';");
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
