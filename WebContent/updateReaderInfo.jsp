<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
   <form name="updateReaderInfo_from" action="updateReaderInfoServlet" method="post" target="_self">
      <table align="center" width="400" cellspacing="5px">
			<tr>
				<td align="center" colspan="2">
					<h3>修改用户信息</h3>
					<hr>
				</td>
			</tr>
			<tr>
				<td  width="160px">用户名：</td>
				<td><c:out value="${NAME}"></c:out></td>
			</tr>
			<tr>
				<td  width="160px">学号/工号：</td>
				<td><c:out value="${RNO}"></c:out></td>
			</tr>
			<tr>
				<td  width="160px">类别：</td>
				<td><c:out value="${RSORT}"></c:out></td>
			</tr>									
			<tr>
				<td  width="160px">性别：</td>
				<td>
				    <input type="radio" name="newrsex" value='男' checked>男&nbsp;
				     <input type="radio" name="newrsex" value='女' checked>女
				</td>
			</tr>
			<tr>
				<td  width="160px">新密码：</td>
				<td><input type="text" name="newPassword"/></td>
			</tr>
			<tr>
				<td  width="160px">年龄：</td>
				<td><input type="text" name="newrage"/></td>
			</tr>
			<tr>
				<td  width="160px">班级：</td>
				<td><input type="text" name="newrClass"/></td>
			</tr>
			
			
			<tr>
				<td  width="160px">电话号码：</td>
				<td><input type="text" name="newrPhoneNum"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="submit" value="确认修改">
				</td>
			</tr>
	     </table>
   </form>
</body>
</html>