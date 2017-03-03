<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书搜索</title>
</head>
<body bgcolor="#FFFFCC">
   <form name="SeekBk_from" action="BookInfoServlet" method="post" target="_self">
      <table align="center"> 
          <tr>
				<td align="center" colspan="4">
					<h3>搜索图书</h3>
					<hr>
				</td>
		  </tr>          
          <tr>
                <td><input name="seek" type="radio" value="模糊查询" checked>模糊查询&nbsp;
                    <input name="seek" type="radio" value="精确查询" >精确查询
                </td>
				<td  width="80px">
				   <select name="select">
				      <option>书名</option>
				      <option>类别</option>
				      <option>条形码</option>
                   </select>
				</td>
				<td><input type="text" name="name" /></td>
				<td align="center" colspan="2">
					<input type="submit" value="搜   索">
				</td>
		  </tr>          
      </table>
   </form>
</body>
</html>