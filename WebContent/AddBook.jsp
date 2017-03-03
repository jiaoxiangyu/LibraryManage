<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图书</title>
</head>
<body bgcolor="#FFFFCC">
	<form name="form1" action="AddBookServlet" method="post" >
		<table align="center" width="400" cellspacing="5px">
			<tr>
				<td align="center" colspan="2">
					<h3>添加图书信息</h3>
					<hr>
				</td>
			</tr>
			<tr>
				<td  width="160px">图书名称：</td>
				<td><input type="text" name="bName"/></td>
			</tr>
			<tr>
				<td width="160px">作者：</td>
				<td><input type="text" name="writer" /></td>
			</tr>
			<tr>
				<td  width="160px">出版社：</td>
				<td><input type="text" name="press" /></td>
			</tr>
			<tr>
				<td  width="160px">出版时间：</td>
				<td><input type="text" name="presstime"  placeholder="格式：2012-01-01" /></td>				
			</tr>
			<tr>
				<td  width="160px">页数：</td>
				<td><input type="text" name="pageNum" /></td>
			</tr>
			<tr>
				<td  width="160px">价格：</td>
				<td><input type="text" name="price" placeholder="格式：0.00" /></td>				
			</tr>
			
			<tr>
				<td  width="160px">类别：</td>
				<td>											
				  <select name="sort">
				     <c:forEach items="${requestScope.bslist}" var="bs">
				         <option>${bs.getBooksort()}</option>
				     </c:forEach>				     
				  </select>
				</td>				
			</tr>
			<tr>
				<td  width="160px">条形码：</td>
				<td><input type="text" name="barCode" /></td>
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