<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
   <form name="SeekRk_from" action="SeekReaderInfoServlet" method="post" target="_self">
      <table align="center"> 
          <tr>
				<td align="center" colspan="2">
					<h3>搜索用户信息</h3>
					<hr>
				</td>
			</tr>          
          <tr>
				<td >用户名：</td>
				<td><input type="text" name="seekName"></td>
		  </tr>		 
		  <tr>
				<td align="center" colspan="2">
					<input type="submit" value="搜   索">
				</td>
		  </tr>          
      </table>
   </form>
</body>
</html>