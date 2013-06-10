<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.actionForm.*" %>
<%
	String account = (String)session.getAttribute("Account");
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
      	<span class="sep">|</span>	 
		<li>
			<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
		</li> 
        <span class="sep">|</span>	
		<li class="current">
			<a href="../EnrolmentInfo/index.jsp" style="padding: 5px 30px 0;">学籍信息</a>
		</li>
		<span class="sep">|</span>		
		<li>
			<a href="../Elective/index.jsp" style="padding: 5px 30px 0;">选课安排</a>
		</li>
		<span class="sep">|</span>	
		<li >
			<a href="../AchievementManagement/index.jsp" style="padding: 5px 30px 0;">成绩管理</a>
		</li>
		<span class="sep">|</span>				
		<li >
			<a href="../Declaration/index.jsp" style="padding: 5px 30px 0;">申报系统</a>
		</li>
		<span class="sep">|</span>				
		<li >
			<a href="../EvalCourse/index.jsp" style="padding: 5px 30px 0;">课程评估</a>
		</li>
        <span class="sep">|</span>				
		<li >
			<a href="../Notification/index.jsp" style="padding: 5px 30px 0;">通知设置</a>
		</li>
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
</div>
<br/><br /><br /><br/>
  <%
  	AcdemicDAO acdemicDAO = new AcdemicDAO();
	Collection stuInfo = acdemicDAO.studentInfo(account);
	String stu_num = "";
	String name_ch = "";
	String gender = "";
	String birth_time = "";
	String id_num = "";
	String college_num = "";
	String major_num = "";
	String entr_time = "";
	String stu_status = "";
	String sch_length = "";
	if (stuInfo != null && !stuInfo.isEmpty()){
		out.println("<table width='75%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>");
		out.println("<tr class='TABLE_TH'><td colspan='10' align='center'>学生信息</td></tr>");
	 	out.println("<tr class='TABLE_TR_01'><td>编号</td><td>姓名</td><td>性别</td><td>出生日期</td><td>学号</td><td>院名称</td><td>系名称</td><td>入学时间</td><td>有无学籍</td><td>学制</td></tr>");
		Iterator it = stuInfo.iterator();
		int flag = 2;
		while(it.hasNext()){
			StudentForm studentForm = (StudentForm)it.next();
			stu_num = studentForm.getStuNum();
			name_ch = studentForm.getNameCh();
			gender = studentForm.getGender();
			birth_time = studentForm.getBirthTime();
			id_num = studentForm.getIdNum();
			college_num = studentForm.getCollegeNum();
			major_num = studentForm.getMajorNum();
			entr_time = studentForm.getEntrTime();
			stu_status = studentForm.getStuStatus();
			sch_length = studentForm.getSchLength();
			if (flag == 2) {
	  			out.println("<tr class='TABLE_TR_02'><td>"+ stu_num +"</td>");
	  			flag = 1;
	  		}
	  		else if (flag == 1) {
	  			out.println("<tr class='TABLE_TR_01'><td>"+ stu_num +"</td>");
	  			flag = 2;
	  		}
	  		out.println("<td>"+ name_ch +"</td>");
			out.println("<td>"+ gender +"</td>");
			out.println("<td>"+ birth_time +"</td>");
			out.println("<td>"+ id_num +"</td>");
			out.println("<td>"+ college_num +"</td>");
			out.println("<td>"+ major_num +"</td>");
			out.println("<td>"+ entr_time +"</td>");
			out.println("<td>"+ stu_status +"</td>");
			out.println("<td>"+ sch_length +"</td></tr>");
		}
		out.println("</table>");
	}
	else 
	{
		out.println("没有学生");
	}  	
  %>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>