<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还  书</title>
</head>
<body bgcolor="#FFFFCC" >
  <form name="returnB_from" action="returnBookServlet" method="post" target="_self">
      <table align="center">  
          <tr>
				<td align="center" colspan="2">
					<h3>归还图书</h3>
					<hr>
				</td>
		  </tr>     
          <tr>
				<td  width="240px">要还的图书的条形码：</td>
				<td><input type="text" name="returnbarCode" /></td>
				
		  </tr> 
		  <tr>
				<td align="center" colspan="2">
					<input type="submit" value="还 书">
				</td>				
		  </tr>           
      </table>
   </form>
</body>
</html>