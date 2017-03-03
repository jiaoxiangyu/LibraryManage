<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
font-family:"微软雅黑";
font-size:24px;
}
</style>
</head>
<body bgcolor="#FFFFCC">
<table height="50" width="1000" align="center">
    <tr align="center">      
       <td height="35" width="110"><a href="LibraryInfo.jsp" target="mainFrame">图书馆信息</a> </td>
       <td height="35" width="100"><a href="BookSeek.jsp" target="mainFrame">图书搜索</a> </td>
       <td height="35" width="100"><a href="AllBookInfo" target="mainFrame">图书库存</a> </td>
       <td height="35" width="100"><a href="ReaderInfoServlet" target="mainFrame">用户信息</a> </td> 
       <td height="35" width="140"><a href="updateReaderInfo.jsp" target="mainFrame">修改用户信息</a> </td>            
       <td height="35" width="100"><a href="LendBooks.jsp" target="mainFrame">借阅图书</a> </td>
       <td height="35" width="100"><a href="returnBook.jsp" target="mainFrame">归还图书</a> </td>      
     </tr>
</table>

</body>
</html>