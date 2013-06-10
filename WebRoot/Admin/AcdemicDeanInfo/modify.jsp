<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	AdminDAO dao = new AdminDAO();
	String pwd = dao.getPwd(account);
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	} 
%>

<script language="javascript">
	function check(form) {
		if (form.newPwd.value != form.cnewPwd.value) {
			alert("两次密码输入不一致！");
			form.cnewPwd.focus();
			return false;
		}
		if (form.oldPwd.value != form.pwd.value) {
			alert("原密码输入不正确！");
			form.oldPwd.focus();
			return false;
		}
	}
</script>
<head>
<title>研究生教务系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="../../JS/jquery-1.3.2.js"></script>
<link href="../../CSS/inner.css" rel="stylesheet" type="text/css" />
<style>
.MenuBar li a:hover,.MenuBar li a:active{border:none;color:#00B5F7;text-decoration:none;}
.MenuBar .sep{padding-top:13px;font-size:10px;color:#aeadad;float:left;height:20px;border-top:none;}
.MenuBar{position:relative;height:28px;float:left;}
.MenuBar .current{color:#008ace;}
.MenuBar .current a{color:#008ace;}
.MenuBar li{float:left;list-style:none;padding-bottom:11px;}
.MenuBar li.back{background:url(../../Images/b_slider.gif) center bottom no-repeat;width:120px;height:28px;z-index:8;position:absolute;}
.MenuBar li a{font:bold 14px arial;text-decoration:none;color:#303030;outline:none;text-align:center;top:6px;letter-spacing:0;z-index:10;display:block;float:left;height:28px;position:relative;overflow:hidden;padding:5px 20px 0 17px;font-family:"Microsoft Yahei",Arial,Helvetica,sans-serif,"微软雅黑";font-weight:normal;font-size:13px;}
.tablehead {
	font-size: 18px;
	font-weight: bold;
}
.tablebody {
	font-size: 14px;
	font-weight: bold;
}
</style>
</head>

<body>

<div id="Header">
	  <ul  id="1" class="MenuBar">	 
      	<span class="sep">|</span>	 
		<li >
			<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
		</li> 
        <span class="sep">|</span>	
		<li >
			<a href="../StudentManagement/index.jsp" style="padding: 5px 30px 0;">院系学生管理</a>
		</li>
		<span class="sep">|</span>		
		<li>
			<a href="../AchievementManagement/index.jsp" style="padding: 5px 30px 0;">院系成绩管理</a>
		</li>
		<span class="sep">|</span>	
		<li>
			<a href="../CourseManagement/index.jsp" style="padding: 5px 30px 0;">院系课程管理</a>
		</li>
        <span class="sep">|</span>				
		<li class="current">
			<a href="../AcdemicDeanInfo/index.jsp" style="padding: 5px 30px 0;">教务员信息管理</a>
		</li>
		<span class="sep">|</span>
		<li>
			<a href="../NotificationManagement/index.jsp" style="padding: 5px 30px 0;">通知管理</a>
		</li>
	 </ul>
	<br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
	
</div>
<br/><br /><br /><br/>
<form id="form1" method="post" action="admin?action=modifyPwd">
<table width='40%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'>
    <td colspan="2" align="center" class="tablehead">修改密码</td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td width="169" align="center" class="tablebody">原密码：</td>
    <td width="325"><label for="oldPwd"></label>
      <input type="password" name="oldPwd" id="oldPwd" /></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td align="center" class="tablebody">新密码：</td>
    <td><label for="newPwd"></label>
      <input type="password" name="newPwd" id="newPwd" /></td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td align="center" class="tablebody">确认新密码：</td>
    <td><label for="cnewPwd"></label>
      <input type="password" name="cnewPwd" id="cnewPwd" /></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td colspan="2" align="center" class="tablebody">
    	<input type="submit" name=" submit" id=" submit" value="提交" onclick="return check(form1)"/>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="reset" name="reset" id="reset" value="重置" /></td>
    </tr>
</table>
<input type="hidden" name="account" value="<%=account %>"/>
<input type="hidden" name="pwd" value="<%=pwd %>"/>
</form>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>