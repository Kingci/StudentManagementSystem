<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.actionForm.*" %>
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	} 
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>研究生教务系统</title>
<script language="javascript">
	function check(form){
		if (form.courseId.value==""){
			alert("请输入课程编号！");
			form.courseId.focus();
			return false;
		}
		if (form.nameC.value==""){
			alert("请输入课程中文名称！");
			form.nameC.focus();
			return false;
		}
		if (form.credit.value == ""){
			alert("请输入学分数！");
			form.credit.focus();
			return false;
		}
		if (form.weekHour.value == ""){
			alert("请输入周学时！");
			form.weekHour.focus();
			return false;
		}
		if (form.semester.value == ""){
			alert("请输入开课时间！");
			form.semester.focus();
			return false;
		}
		if (form.collegeId.value == ""){
			alert("请输入开课院系编号！");
			form.collegeId.focus();
			return false;
		}
		if (form.courseYear.value == ""){
			alert("请输入学期！");
			form.courseYear.focus();
			return false;
		}
	}
</script>
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
		<li >
			<a href="../AchievementManagement/index.jsp" style="padding: 5px 30px 0;">院系成绩管理</a>
		</li>
		<span class="sep">|</span>	
		<li class="current">
			<a href="../CourseManagement/index.jsp" style="padding: 5px 30px 0;">院系课程管理</a>
		</li>
        <span class="sep">|</span>				
		<li >
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

<form id="form1" method="post" action="admin?action=insertCourse">

  <table width='55%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'>
    <td colspan="6" align="center">课程添加</td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td >课程编号</td>
    <td ><label for="courseId"></label>
      <input type="text" name="courseId" id="courseId" value=""/></td>
    <td >课程中文名</td>
    <td><label for="nameC"></label>
      <input type="text" name="nameC" id="nameC" value="" /></td>
    <td>课程英文名</td>
    <td><label for="nameE"></label>
      <input name="nameE" type="text" id="nameE" value=""/></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td >学分数</td>
    <td><label for="credit"></label>
      <input type="text" name="credit" id="credit" value=""/></td>
    <td >周学时</td>
    <td><label for="weekHour"></label>
      <input type="text" name="weekHour" id="weekHour" value="" /></td>
    <td >学期</td>
    <td colspan="3"><label for="semester"></label>
      <input type="text" name="semester" id="semester" value="" /></td>
  </tr>
  <tr class='TABLE_TR_01'>
  	<td >教学形式</td>
    <td><label for="teachMode"></label>
      <input type="text" name="teachMode" id="teachMode" value="" /></td>
    <td >开课院系</td>
    <td><label for="teachMode"></label>
      <input type="text" name="collegeId" id="collegeId" value="" /></td>
    <td >学年</td>
    <td colspan="3"><label for="courseYear"></label>
      <input type="text" name="courseYear" id="courseYear" value="" /></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td colspan="6" align="center"><input type="submit" name="insert" id="insert" value="添加" onclick="return check(form1)"/></td>
  </tr>
</table>
<input type="hidden" name="account" value="<%=account %>"/>
</form>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>