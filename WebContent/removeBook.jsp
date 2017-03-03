<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除图书</title>
</head>
<body bgcolor="#FFFFCC" >
  <form name="removeB_from" action="removeBookServlet" method="post" target="_self" >
      <table align="center"> 
          <tr>
				<td align="center" colspan="2">
					<h3>删除图书</h3>
					<hr>
				</td>
		  </tr>
          <tr>
				<td  width="160px">图书名称：</td>
				<td><input type="text" name="rmbookName" /></td>				
		  </tr>  
		  <tr>
				<td  width="160px">条形码：</td>
				<td><input type="text" name="barCode" /></td>				
		  </tr>            
		  <tr>
				<td align="center" colspan="2">
					<input type="submit" value="删除">
				</td>				
		  </tr>           
      </table>
   </form>
</body>
</html>