<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加读者</title>
</head>
<body  bgcolor="#FFFFCC">
   <form name="form2" action="AddReaderServlet" method="post" >
		<table align="center" width="400" cellspacing="5px">
			<tr align="center">
				<td align="center" colspan="2">
					<h3>添加用户信息</h3>
					<hr>
				</td>
			</tr>
			<tr>
				<td  width="160px">用户名：</td>
				<td><input type="text" name="rName"/></td>
			</tr>			
			<tr>
				<td  width="160px">性别：</td>
				<td>
				     <input type="radio" name="rsex" value='男' checked>男&nbsp;
				     <input type="radio" name="rsex" value='女' checked>女				     
				</td>
			</tr>
			<tr>
				<td  width="160px">年龄：</td>
				<td><input type="text" name="rage"/></td>
			</tr>
			<tr>
				<td  width="160px">班级：</td>
				<td><input type="text" name="rClass"/></td>
			</tr>
			<tr>
				<td  width="160px">学号：</td>
				<td><input type="text" name="rNo"/></td>
			</tr>
			<tr>
				<td  width="160px">类别：</td>
				<td><input type="text" name="rSort"/></td>
			</tr>
			<tr>
				<td  width="160px">电话号码：</td>
				<td><input type="text" name="rPhoneNum"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="添　加">
				</td>
			</tr>
	     </table>
    </form>
</body>
</html>