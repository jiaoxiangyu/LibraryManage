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

import com.lyq.bean.Book;
import com.lyq.bean.MysqlLinking;

import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookInfoServlet",urlPatterns ="/BookInfoServlet")//注释
public class BookInfoServlet extends HttpServlet {
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
		String select = request.getParameter("select");	//获得表单属性
		String seek= request.getParameter("seek");
		if(select.equals("书名")){//判断select的值
			select="bName";
		}
		else if(select.equals("类别")){
			select="sort";
		}
		else if(select.equals("条形码")){
			select="barCode";
		}
		String name = request.getParameter("name");	
		List<Book> list=new ArrayList<>();
   	 if (conn != null) {
			try {
				String sql="";
				PreparedStatement ps=null;
				if(seek.equals("模糊查询")){
					sql="select * from books where "+select+" like '%"+name+"%'" ;
					ps = conn.prepareStatement(sql);	
				}
				else if(seek.equals("精确查询")){
					sql="select * from books where "+select+"=?"; //根据select的属性值查询图书
					//创建PreparedStatement对象
				    ps = conn.prepareStatement(sql);				
				    ps.setString(1, name);
				}
				
				
				ResultSet rs=ps.executeQuery();	
				
				while(rs.next()){	//遍历所有图书把符合条件的图书对象存入list集合
					Book b=new Book();
					b.setbName(rs.getString("bName"));					
					b.setwriter(rs.getString("writer"));
					b.setpress(rs.getString("press"));
					b.setpresstime(rs.getString("presstime"));
					b.setbarCode(rs.getString("barCode"));
					b.setsort(rs.getString("sort"));
					b.setstate(rs.getString("state"));
					b.setprice(rs.getDouble("price"));
					b.setpageNum(rs.getInt("pageNum"));	
					b.setLendNum(rs.getInt("LendNum"));
					b.setBorrower(rs.getString("borrower"));
					list.add(b);
				}
				request.setAttribute("list", list);	//把list存在request对象中
				String bookname="";
				for(Book bs:list){
					bookname=bs.getbName();
				}
				if(bookname.equals("")){//判断搜索结果是否为空
					PrintWriter out = response.getWriter();
					//输出注册结果信息			
					out.println("<script language='javascript'>");
					out.println("var str='未搜索到你要的内容！';");
					out.println("alert(str);");	
					out.println("window.navigate('BookSeek.jsp');");
					out.println("</script>");
					out.flush();
					out.close();	
				}
				else{
					request.getRequestDispatcher("/WEB-INF/BookInfo.jsp").forward(request, response);//转到/WEB-INF/BookInfo.jsp页面 
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
