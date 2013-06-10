<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<%
	StudentDAO studentDAO = new StudentDAO();
	Collection stuInfo = studentDAO.studentInfo(account);
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
		Iterator it = stuInfo.iterator();
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
	}
	else 
	{
		System.out.println("stuInfo is empty!");
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
      			<li >
					<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
				</li> 
                <span class="sep">|</span>	
				<li class="current">
					<a href="../StudentInfo/index.jsp" style="padding: 5px 30px 0;">个人信息</a>
				</li>
				<span class="sep">|</span>		
				<li >
					<a href="../Elective/index.jsp" style="padding: 5px 30px 0;">选课安排</a>
				</li>
				<span class="sep">|</span>	
				<li >
					<a href="../AchievementInfo/index.jsp" style="padding: 5px 30px 0;">成绩查看</a>
				</li>
				<span class="sep">|</span>				
				<li >
					<a href="../EvalCourse/index.jsp" style="padding: 5px 30px 0;">课程评估</a>
				</li>
                <span class="sep">|</span>				
	  			<li >
					<a href="../Declaration/index.jsp" style="padding: 5px 30px 0;">申报系统</a>
				</li>				
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
</div>
<br/><br /><br />
<table table width="65%"  class="TABLE_BODY" bordercolor="#777777" border="1" style="border-color:#777777;border-collapse:collapse" align="center">
  <tr class="TABLE_TH" >
    <td colspan="6" align="center">基本信息</td>
  </tr>
  <tr class="TABLE_TR_01">
    <td >学号</td>
    <td ><%=stu_num %></td>
    <td >姓名</td>
    <td ><%=name_ch %></td>
    <td >性别</td>
    <td ><%=gender %></td>
  </tr>
  <tr class="TABLE_TR_02">
    <td>出生日期</td>
    <td><%=birth_time %></td>
    <td>身份证号</td>
    <td colspan="3"><%=id_num %></td>
  </tr>
  <tr class="TABLE_TR_01">
    <td>所在院系</td>
    <td><%=college_num %></td>
    <td>所在专业</td>
    <td><%=major_num %></td>
    <td>专业方向</td>
    <td>&nbsp;</td>
  </tr>
  <tr class="TABLE_TR_02">
    <td>入学年份</td>
    <td><%=entr_time %></td>
    <td>学籍状态</td>
    <td><%=stu_status %></td>
    <td>学制</td>
    <td><%=sch_length %></td>
  </tr>
</table>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>