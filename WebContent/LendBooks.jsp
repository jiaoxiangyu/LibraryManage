<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借  书</title>
</head>
<body bgcolor="#FFFFCC">
  <form name="lendB_from" action="LendBookServlet" method="post" target="_self">
      <table align="center">   
          <tr>
				<td align="center" colspan="2">
					<h3>借阅图书</h3>
					<hr>
				</td>
			</tr>       
		  <tr>
				<td  width="220px">要借图书的条形码：</td>
				<td><input type="text" name="barCode" /></td>				
		  </tr> 
		  <tr>
				<td align="center" colspan="2">
					<input type="submit" value="借  阅">
				</td>				
		  </tr>           
      </table>
   </form>
</body>
</html>