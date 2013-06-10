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
      	<li>
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
		<li >
			<a href="../CourseManagement/index.jsp" style="padding: 5px 30px 0;">院系课程管理</a>
		</li>
       	<span class="sep">|</span>				
 		<li >
			<a href="../AcdemicDeanInfo/index.jsp" style="padding: 5px 30px 0;">教务员信息管理</a>
		</li>
		<span class="sep">|</span>
		<li class="current">
			<a href="../NotificationManagement/index.jsp" style="padding: 5px 30px 0;">通知管理</a>
		</li>
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
</div>
<br/><br /><br /><br/>
<form id="form1" method="post" action="admin?action=deleteNotification">
<%
  	AdminDAO adminDAO = new AdminDAO();
	Collection courInfo = adminDAO.queryNotification();
	int notificationId = -1;
	String authorId = "";
	int collegeId = -1;
	String title = "";
	String content = "";
	
	if (courInfo != null && !courInfo.isEmpty()){
		out.println("<table width='55%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>");
		out.println("<tr class='TABLE_TH'><td colspan='4' align='center'>已发布通知</td></tr>");
		out.println("<tr class='TABLE_TR_01'><td>通知发布人</td><td width='300'>通知标题</td><td>通知内容</td><td>选择</td></tr>");
		Iterator it = courInfo.iterator();
		int flag = 2;
		while(it.hasNext()){
			NotificationForm notificationForm = (NotificationForm)it.next();
			notificationId = notificationForm.getNotificationId();
			authorId = notificationForm.getAuthorId();
			collegeId = notificationForm.getCollegeId();
			title = notificationForm.getTitle();
			content = notificationForm.getContent();
	  		if (flag == 2) {
		  			out.println("<tr class='TABLE_TR_02'><td valign='middle' width='20%'>"+ authorId +"</td>");
		  			flag = 1;
	  		}
	  		else if (flag == 1) {
	  			out.println("<tr class='TABLE_TR_01'><td valign='middle' width='20%'>"+ authorId +"</td>");
	  			flag = 2;
	  		}
	  		out.println("<td width='20%'>"+ title +"</td>");
	  		out.println("<td width='50%'>" + content + "</td>");
			out.println("<td width='10%'><input type='checkbox' name='"+notificationId+"' id='"+notificationId+"'/></td></tr>");
  		}
  		if (flag == 1)
  			out.println("<tr class='TABLE_TR_01'><td colspan='4' align='center'><input type='submit' name='delete' id='delete' value='删除' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
  					"<input type='button' name='return' id='return' value='返回' onClick=\"window.location.href='index.jsp'\"/></td></tr></table>");
  		else 
  			out.println("<tr class='TABLE_TR_02'><td colspan='4' align='center'><input type='submit' name='delete' id='delete' value='删除' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
  					"<input type='button' name='return' id='return' value='返回' onClick=\"window.location.href='index.jsp'\"/></td></tr></table>");
	}
	else  
	{
		out.println("没有发布通知");
		System.out.println("Notification is empty!");
	}
%>
<input type="hidden" name="account" value="<%=account %>"/>
</form>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>