<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.StudentDAO" %>
<%@ page import="com.actionForm.AchievementInfoForm" %>
<%@ page import="com.action.Student" %>
<%@ page import="java.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	}
	StudentDAO achiv_info_dao = new StudentDAO();
	Student achiv_info = new Student();
	String stu_id =  account;
	Collection coll= achiv_info_dao.query(stu_id);
	
	String first_year = achiv_info.getFirstYear(stu_id);
	String first_term = achiv_info.getFirstTerm(stu_id);
	String current_year = achiv_info.getCurrentYear(stu_id);
	String current_term = achiv_info.getCurrentTerm(stu_id);
	
	Collection credit_coll = achiv_info_dao.query_credit_by_type(stu_id);
	float total = 0;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>研究生教务系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="../../JS/jquery-1.3.2.js"></script>
<link href="../../CSS/inner.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.header  {
	width: 972px;
	height: 50px;
	background: url(./studentLogo.jpg) no-repeat;
	margin: 0 auto;
}
.sidebar {
	width: 239px;
	height: 739px;
	border: #e1e1e1 1px solid;
	float: left;
	background: #FFF;
	margin: 0 auto;
	margin-top: 7px;
}
.siderbar .title {
	width: 234px;
	height: 36px;
	font-family: "微软雅黑";
	font-size: 14px;
	line-height: 36px;
	padding-left: 5px;
}
.content {
	margin: 0 auto;
	margin-top: 7px;
	width: 715px;
	height: 739px;
	border: #e1e1e1 1px solid;
	float: left;
	margin-left: 10px;
	background: #FFF;	
}
.MenuBar li a:hover,.MenuBar li a:active{border:none;color:#00B5F7;text-decoration:none;}
.MenuBar .sep{padding-top:13px;font-size:10px;color:#aeadad;float:left;height:20px;border-top:none;}
.MenuBar{position:relative;height:28px;float:left;}
.MenuBar .current{color:#008ace;}
.MenuBar .current a{color:#008ace;}
.MenuBar li{float:left;list-style:none;padding-bottom:11px;}
.MenuBar li.back{background:url(../../Images/b_slider.gif) center bottom no-repeat;width:120px;height:28px;z-index:8;position:absolute;}
.MenuBar li a{font:bold 14px arial;text-decoration:none;color:#303030;outline:none;text-align:center;top:6px;letter-spacing:0;z-index:10;display:block;float:left;height:28px;position:relative;overflow:hidden;padding:5px 20px 0 17px;font-family:"Microsoft Yahei",Arial,Helvetica,sans-serif,"微软雅黑";font-weight:normal;font-size:13px;}
</style></head>

<body>
<div id="Header">
	  <ul  id="1" class="MenuBar">	 
      			<li >
					<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
				</li> 
                <span class="sep">|</span>	
				<li >
					<a href="../StudentInfo/index.jsp" style="padding: 5px 30px 0;">个人信息</a>
				</li>
				<span class="sep">|</span>		
				<li >
					<a href="../Elective/index.jsp" style="padding: 5px 30px 0;">选课安排</a>
				</li>
				<span class="sep">|</span>	
				<li class="current">
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
<br/><br /><br /><br/>


<table width='40%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'><td colspan='2' align='center' >学分统计</td></tr>
  <tr class='TABLE_TR_01'>
    <td >课程类型</td>  
    <td >学分总数</td>
    </tr>
    <%
	Iterator it = credit_coll.iterator();
	int flag = 2;
	while(it.hasNext()) {
		float credit;
		String course_type;
		
		AchievementInfoForm obj = (AchievementInfoForm)it.next();
		credit = obj.getCourse_credit();
		course_type = obj.getCourse_type();
		total += credit;
		if (flag == 2) {
			out.println("<tr class='TABLE_TR_02'>");
			out.println("<td >" + course_type + "</td><td >" + credit +"</td></tr>");		
			flag = 1;
		}
		else if (flag == 1){
			out.println("<tr class='TABLE_TR_01'>");
			out.println("<td >" + course_type + "</td><td >" + credit +"</td></tr>");	
			flag = 2;
		}
    

	}
	String total_s = "总计"; 
%>
<% if (flag == 2) {%>
  <tr class='TABLE_TR_02'>
    <td ><%=total_s%></td>
    <td ><%=total%></td>
    </tr>
    <%} %>
<% if (flag == 1) {%>
  <tr class='TABLE_TR_01'>
    <td ><%=total_s%></td>
    <td ><%=total%></td>
    </tr>
    <%} %>
</table>


</body>
</html>