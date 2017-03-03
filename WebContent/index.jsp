<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>

<script language="javascript">
	function check(){
		if(form1.username.value==""){				//判断用户名是否为空
			alert("请输入用户名！");
			form1.user.focus();
			return;
		}else if(form1.pwd.value==""){			//判断密码是否为空
			alert("请输入密码！");
			form1.pwd.focus();
			return;
		}else{
			form1.submit();					//提交表单
		}
	}
</script>


</head>
<body bgcolor="#FFFFCC">
<form action="LoginServlet" method="post" name="form1" >
<table width="400" border="0" align="center" cellpadding="2" cellspacing="2"> 
          <tr >          
            <td width="120" height="40"  align="center">用户类别：</td>
            <td colspan="2">
                <select name="UserSort" size="1"> 
                <option>学生</option>
                <option>管理员</option>                
            </select>            
          </tr>          
          <tr >          
            <td width="120" height="40"  align="center">用户名：</td>
            <td colspan="2"><input name="username" type="text"  maxlength="20"></td>
          </tr>
          <tr>
            <td  width="120" height="40" align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            <td  colspan="2"><input name="pwd" type="password"  size="20" maxlength="20"></td>
          </tr>        
          <tr>
            <td >&nbsp;</td>
            <td  width="200" height="40" width="100%" class="word_grey"><input name="Submit" type="submit" class="btn_grey" value="登 录" onclick="check()">        
          </tr>   
</table>    
</form>
</body>
</html>
