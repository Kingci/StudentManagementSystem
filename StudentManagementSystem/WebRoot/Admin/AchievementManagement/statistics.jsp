<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	} 
	String stu_num = "";
	String course_id = "";
	String score = "";
%>
<head>
<title>研究生教务系统</title>
<script language="javascript">
	function check(form){
		if (form.stu_num.value==""){
			alert("请输入学号！");
			form.stu_num.focus();
			return false;
		}
		if (form.course_id.value==""){
			alert("请输入课程号！");
			form.course_id.focus();
			return false;
		}
		if (form.score.value==""){
			alert("请输入分数！");
			form.score.focus();
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
		<li>
			<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
		</li> 
              <span class="sep">|</span>
		<li>
			<a href="../EnrolmentInfo/index.jsp" style="padding: 5px 30px 0;">学籍信息</a>
		</li>
		<span class="sep">|</span>		
		<li>
			<a href="../Elective/index.jsp" style="padding: 5px 30px 0;">选课安排</a>
		</li>
		<span class="sep">|</span>	
		<li class="current">
			<a href="../AchievementManagement/index.jsp" style="padding: 5px 30px 0;">成绩管理</a>
		</li>
		<span class="sep">|</span>				
		<li>
			<a href="../Declaration/index.jsp" style="padding: 5px 30px 0;">申报系统</a>
		</li>
        <span class="sep">|</span>				
		<li>
			<a href="../Notification/index.jsp" style="padding: 5px 30px 0;">通知设置</a>
		</li>
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
<br/><br /><br /><br/>

<div id="Function">
<form id="form1" name="form1" method="post" action="admin?action=addgrades">
<table width="60%"  border="1" cellpadding="0" cellspacing="1" align="center" bordercolor="#999999" bordercolordark="#FFFFFF" bordercolorlight="#FFFFFF">
  <tr align="left" bgcolor="#FFFFFF">
    <td width="30%">学号</td>  
    <td width="30%">课程编号</td>
    <td width="30%">分数</td>
   </tr>
   <tr>
    <td><input type="text" name="stu_num" id="stu_num" value="<%=stu_num %>" style="border-style: none;height:30px;width:275px"/></td>
    <td><input type="text" name="course_id" id="course_id" value="<%=course_id %>" style="border-style: none;height:30px;width:275px"/></td>
    <td><input type="text" name="score" id="score" value="<%=score %>" style="border-style: none;height:30px;width:275px"/></td>
   </tr>
   <tr>
     <td colspan="4" style="padding:5px;">
     	<input type="submit" name="submit" id="submit" value="添加" onclick="return check(form1)"/>
     </td>
   </tr>
</table>
</form>
</div>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>