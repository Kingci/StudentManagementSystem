<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
body {
	background-image: url(Images/background.jpg);
	background-repeat: no-repeat;
}
#apDiv1 {
	position:absolute;
	width:322px;
	height:108px;
	layer-background-color:#F06;
	border:1px none #000000;
	z-index:1;
	left: 547px;
	top: 337px;
}
ys01 {
	font-weight: bold;
}
ys01 {
	font-weight: bold;
}
</style>

<title>研究生教务系统</title>
<script language="javascript">
	function check(form){
		if (form.name.value==""){
			alert("请输入账号！");
			form.name.focus();
			return false;
		}
		if (form.pwd.value==""){
			alert("请输入密码！");
			form.pwd.focus();
			return false;
		}
		if (form.rand.value == ""){
			alert("请输入验证码！");
			form.rand.focus();
			return false;
		}
	}
	
	function refresh() {
		var img = document.getElementById("checkImg");  
  		img.src=img.src+"?"; 
	} 
</script>
</head>

<body>
<form id="form1" name="form1" method="post" action="account?action=login">
<div id="apDiv1">
  <table width="100%" border="0" cellspacing="10" cellpadding="0">
    <tr>
      <td width="29%" align="center" valign="middle" scope="col">用户名</td>
      <th width="71%" align="left" scope="col"><input type="text" name="name" id="name" />
      </th>
    </tr>
    <tr>
      <td align="center" valign="middle">密码</td>
      <td align="left"><input type="password" name="pwd" id="pwd" /></td>
    </tr>
    <tr>
      <td align="center" valign="middle">验证码</td>
      <td align="left"><input type="text" name="rand" maxlength="4" value="" size="6"/>
        <img border="1" id="checkImg" src="image.jsp" onclick="refresh();" alt="3"/>
         <a href="#" onclick="javascript:refresh();"/>换一张</a></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
      <input type="submit" name="login" id="login" value="登陆" onclick="return check(form1)"/>
      &nbsp; 
      <input type=reset value="重置"/></td>
    </tr>
  </table>
</div>
</form>
</body>
</html>