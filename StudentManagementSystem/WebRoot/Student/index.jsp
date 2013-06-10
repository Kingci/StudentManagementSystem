<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.actionForm.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%
	String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../index.jsp");
	} 
%>
<title>研究生教务系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="../JS/jquery-1.3.2.js"></script>
<link href="../CSS/inner.css" rel="stylesheet" type="text/css" />
<style>
.MenuBar li a:hover,.MenuBar li a:active{border:none;color:#00B5F7;text-decoration:none;}
.MenuBar .sep{padding-top:13px;font-size:10px;color:#aeadad;float:left;height:20px;border-top:none;}
.MenuBar{position:relative;height:28px;float:left;}
.MenuBar .current{color:#008ace;}
.MenuBar .current a{color:#008ace;}
.MenuBar li{float:left;list-style:none;padding-bottom:11px;}
.MenuBar li.back{background:url(../Images/b_slider.gif) center bottom no-repeat;width:120px;height:28px;z-index:8;position:absolute;}
.MenuBar li a{font:bold 14px arial;text-decoration:none;color:#303030;outline:none;text-align:center;top:6px;letter-spacing:0;z-index:10;display:block;float:left;height:28px;position:relative;overflow:hidden;padding:5px 20px 0 17px;font-family:"Microsoft Yahei",Arial,Helvetica,sans-serif,"微软雅黑";font-weight:normal;font-size:13px;}
.Locate{ margin: 200px 700px 100px 80px}
</style>
</head>

<body>

<div id="Header">
	  <ul  id="1" class="MenuBar">	 
      			<li class="current">
					<a href="index.jsp" style="padding: 5px 30px 0;">首页</a>
				</li> 
                <span class="sep">|</span>	
				<li >
					<a href="StudentInfo/index.jsp" style="padding: 5px 30px 0;">个人信息</a>
				</li>
				<span class="sep">|</span>		
				<li >
					<a href="Elective/index.jsp" style="padding: 5px 30px 0;">选课安排</a>
				</li>
				<span class="sep">|</span>	
				<li >
					<a href="AchievementInfo/index.jsp" style="padding: 5px 30px 0;">成绩查看</a>
				</li>
				<span class="sep">|</span>				
				<li >
					<a href="EvalCourse/index.jsp" style="padding: 5px 30px 0;">课程评估</a>
				</li>
                <span class="sep">|</span>				
	  			<li >
					<a href="Declaration/index.jsp" style="padding: 5px 30px 0;">申报系统</a>
				</li>				
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../Images/Icon_Exit.gif'/><a href="../logout.jsp">退出</a>
	</div>
	
</div>
<br/><br /><br /><br/>

<div id="Function">
  <ul>
	    <li id="studentinfo"><a href="StudentInfo/index.jsp"><img src="../Images/personal.png" /><br />个人信息</a></li>
    <li id="electiveinfo"><a href="Elective/index.jsp"><img src="../Images/elective.png" /><br />选课安排</a></li>
    <li id="achievementinfo"><a href="AchievementInfo/index.jsp"><img src="../Images/achievement.png" /><br />成绩查看</a></li>
	<li id="evalcourse"><a href="EvalCourse/index.jsp"><img src="../Images/stueval.png" /><br />课程评估</a></li>
	<li id="declaration"><a href="Declaration/index.jsp"><img src="../Images/elective_public.png"/><br />申报系统</a></li>
  </ul>
</div>
<%
	if(account != null){
		StudentDAO studentDAO = new StudentDAO();
		Collection courInfo = studentDAO.queryNotification(account.substring(2,5));
		int notificationId = -1;
		String authorId = "";
		int collegeId = -1;
		String title = "";
		String content = "";
		
		if (courInfo != null && !courInfo.isEmpty()){
			out.println("<table width='350' class='Locate' cellpadding='0' cellspacing='1' align='center'>");
			//out.println("<tr height='30'><td colspan='3' align='center'>已发布通知</td></tr>");
			//out.println("<tr height='40'><td width='100'><font color='#FF0000'><b>通知标题</b></font></td><td width='250'><font color='#0000FF'>通知内容</font></td></tr>");
			Iterator it = courInfo.iterator();
			while(it.hasNext()){
				NotificationForm notificationForm = (NotificationForm)it.next();
				notificationId = notificationForm.getNotificationId();
				authorId = notificationForm.getAuthorId();
				collegeId = notificationForm.getCollegeId();
				title = notificationForm.getTitle();
				content = notificationForm.getContent();
		  		//out.println("<tr height='30'><td>"+ notificationId +"</td>");
		  		out.println("<td><font color='#FF0000'><b>["+ title +"]</b></font></td>");
				out.println("<td><font color='#0000FF'>"+content+"</font></td></tr>");
	  		}
	  		out.println("</table>");
		}
		else 
		{
			System.out.println("Notification is empty!");
		}
	}
 %>
<script type="text/javascript" src="../JS/flash.js"></script>
</body>
</html>