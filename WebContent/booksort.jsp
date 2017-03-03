<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.one{
font-family:"微软雅黑";
font-size:24px;
}
.two{
font-family:"宋体" 
font-size:20px;  
}
</style>
</head>
<body bgcolor="#FFFFCC">
      <from >
         <table align="center" bgcolor="black" cellpadding="1" cellspacing="1" >             
             <tr bgcolor="yellow">
                 <td class="one">图书所有类型</td>
                 <c:forEach items="${requestScope.bslist2}" var="bs2">
				         <td class="two">${bs2.getBooksort()}</td>
			     </c:forEach>	             
             </tr>
         </table>
      </from>
      <form name="sortForm_1" action="addBookSortServlet" method="post" target="_self">
           <table align="center">
               <tr>
                  <td class="one">添加图书类别</td>
               </tr>
               <tr>
                  <td><input type="text" name="bsort" /></td>
				  <td align="center" colspan="2">
					  <input type="submit" value="添 加">
				  </td>
			   </tr>
           </table>
      </form>
      <form name="sortForm_2" action="removeBookSortServlet" method="post" target="_self">
           <table align="center">
               <tr>
                  <td class="one">删除图书类别</td>
               </tr>
               <tr>
                  <td><input type="text" name="bsort2" /></td>
				  <td align="center" colspan="2">
					  <input type="submit" value="删 除">
				  </td>
			   </tr>
           </table>
      </form>
</body>
</html>