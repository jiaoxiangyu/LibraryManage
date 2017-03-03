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
import javax.servlet.http.HttpSession;

import com.lyq.bean.Reader;
import com.lyq.bean.Lendbook;
import com.lyq.bean.MysqlLinking;

@WebServlet(name = "ReaderInfoServlet",urlPatterns ="/ReaderInfoServlet")//注释配置文件
public class ReaderInfoServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		String rno = (String) session.getAttribute("RNO");	//获得用户学号/工号
		List<Reader> list=new ArrayList<>();//创建Reader对象集合
		List<Lendbook> list2=new ArrayList<>();//创建LendBook集合
   	 if (conn != null) {
			try {							
				String sql="select * from reader where rNo=? "; 
				//创建PreparedStatement对象
				PreparedStatement ps = conn.prepareStatement(sql);				
				ps.setString(1, rno);				   	
				ResultSet rs=ps.executeQuery();	
				
				while(rs.next()){	//获得用户信息
					Reader r=new Reader();
					r.setrName(rs.getString("rName"));
					r.setrsex(rs.getString("rsex"));
					r.setrage(rs.getInt("rage"));
					r.setrClass(rs.getString("rClass"));
					r.setrSort(rs.getString("rSort"));
					r.setrPhoneNum(rs.getString("rPhoneNum"));
					r.setrNo(rs.getString("rNo"));
					r.setLendNum(rs.getInt("lendNum"));
					r.setCanLendNum(rs.getInt("canLendNum"));					
					list.add(r);
				}
				
				String sql2="select * from lendbooks where rNo=? "; //获得用户借阅的图书
				//创建PreparedStatement对象
				PreparedStatement ps2 = conn.prepareStatement(sql2);				
				ps2.setString(1, rno);			   	
				ResultSet rs2=ps2.executeQuery();					
				while(rs2.next()){	
					Lendbook l=new Lendbook();
					l.setLendbook(rs2.getString("lendbook"));
					l.setBarCode(rs2.getString("barCode"));
					list2.add(l);
				}
				request.setAttribute("list", list);	//把	用户信息储存在request对象中
				request.setAttribute("list2", list2);	
				request.getRequestDispatcher("/ReaderInfo.jsp").forward(request, response);//转换到/ReaderInfo.jsp页面		                			
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
