<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.AcdemicDAO" %>
<%@ page import="com.actionForm.ShowGradesForm" %>
<%@ page import="java.util.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	}
	String key = (String)session.getAttribute("keyword")==null ? "" : (String)session.getAttribute("keyword");
	String stu_num = (String)session.getAttribute("getgrade_stu_num")==null ? "" : (String)session.getAttribute("getgrade_stu_num");
	String stu_name = (String)session.getAttribute("getgrade_stu_name")==null?"" : (String)session.getAttribute("getgrade_stu_name");
	String entr_time = (String)session.getAttribute("getgrade_entr_time")==null?"" : (String)session.getAttribute("getgrade_entr_time");
	String college = account;//(String)session.getAttribute("getgrade_college")==null?"" : (String)session.getAttribute("getgrade_college");
	String course_id = (String)session.getAttribute("getgrade_course_id")==null?"":(String)session.getAttribute("getgrade_course_id");
	
	/* Get query result by functions of AcdemicDAO */
	AcdemicDAO acdemicDAO = new AcdemicDAO();
	Collection coll = acdemicDAO.QueryGrades(key, stu_num, stu_name, entr_time, college, course_id);
%>
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
<br/><br /><br />
<div align="center" class="content">
<%
if(coll!=null&&!coll.isEmpty()) {
%>
<table width='65%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'>
    <td width="8%">学号</td>  
    <td width="8%">姓名</td>
    <td width="8%">年级</td>
    <td width="20%">院系</td>
    <td width="10%">课程编号</td>
    <td width="20%">课程名称</td>
    <td width="5%">学分</td>
   	<td width="10%">课程类型</td>
   	<td width="5%">总评</td>
    </tr>
    <%
	Iterator it = coll.iterator();
	int flag = 1;
	while(it.hasNext()) {
		ShowGradesForm obj = (ShowGradesForm)it.next();
		if (flag == 1) {
				out.println("<tr class='TABLE_TR_01'>");
				flag = 2;
			}
			else if (flag == 2) {
				out.println("<tr class='TABLE_TR_02'>");
				flag = 1;
			}
	%> 
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getStu_num()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getStu_name()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getEntr_time()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getCollege()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getCourse_id()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getCourse_name()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getCredit()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getTeach_mode()%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(Float.parseFloat(obj.getScore()) < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=obj.getScore()%></font></td>
    </tr>
<%
	}
%>
</table>
<%
}
else {
%>
<p>暂无内容！请检查输入。 </p>
<%
} 
%>
</div>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>