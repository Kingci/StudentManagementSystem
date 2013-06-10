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
	
	String first_year = achiv_info.getFirstYear(stu_id);
	System.out.println("first_year:" + first_year);
	String first_term = achiv_info.getFirstTerm(stu_id);
	System.out.println("first_term:" + first_term);
	String current_year = achiv_info.getCurrentYear(stu_id);
	System.out.println("current_year:" + current_year);
	String current_term = achiv_info.getCurrentTerm(stu_id);
	System.out.println("current_term:" + current_term);
	
	String query_year = (String)session.getAttribute("achi_year");
	String query_term = (String)session.getAttribute("achi_term");
	System.out.println("query_year:" + query_year + ",query_term:"  + query_term);
	Collection coll1;
	if(query_year == null || "".equals(query_year) || query_term == null || "".equals(query_term)) {
		coll1=null;
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	//System.out.println("query year: " + query_year);
	//System.out.println("query term: " + query_term);
	else
		coll1 = achiv_info_dao.querySpecificTerm(stu_id, query_year, query_term);
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
</style>
<script type="text/javascript" src="../../JS/flash.js"></script>
<script type="text/javascript" src="../../JS/jquery-1.3.2.js"></script>
<link href="../../CSS/inner.css" rel="stylesheet" type="text/css" />
</head>

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
<br/><br /><br /><br/>
<div class="sidebar">
  <div class="title">选择学期</div>
  <ul>
  <%
 // System.out.println("up < down ? " + "上学期".compareTo("下学期")); 
  if(coll1!=null){
  Collection coll_y = achiv_info_dao.query_y_s(stu_id);
  Iterator i_y = coll_y.iterator();
  AchievementInfoForm y = null;
  String year_t = "";
  String seme_t = "";
  while(i_y.hasNext()) {
	  y = (AchievementInfoForm)i_y.next();
	  year_t = y.getCourse_year();
	  if((y.getCourse_term()).equals("上学期"))
		  seme_t = "1";
	  else
		  seme_t = "2";
	 // System.out.println("semester:" + seme_t);
	  out.print("<li>");
	  out.print("<a href=\""+ 
		  			"student?action=querygrades&year="
		  			+ year_t
		  			+"&term="
		  			+ seme_t
		  			+ "\">");
		  out.print("<span>");
		  out.print(y.getCourse_year() + y.getCourse_term());
		  out.print("</span>");
		  out.print("</a>");
		  out.print("</li>");
	  }
  }
  %>
  <!-- <li><a href="info?action=querygrades&year=<%="2011"%>&term=<%="2"%>">ABC</a></li> -->
  </ul>
</div>
<div class="content">
<table width='100%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr align="center" class='TABLE_TH'>
    <td >课程编号</td>  
    <td >课程名称</td>
    <td >类型</td>
    <td >学分</td>
    <td >分数</td>
    </tr>
    <%
    if(coll1!=null){
	Iterator it = coll1.iterator();
	int flag = 1;
	while(it.hasNext()) {
		float score;
		String student_id;
		String course_id;
		String course_year;
		String course_term;
		
		String course_name;
		String course_type;
		String course_credit;
		
		AchievementInfoForm obj = (AchievementInfoForm)it.next();
		student_id = obj.getStu_id();
		course_id = obj.getCourse_id();
		course_year = obj.getCourse_year();
		course_term = obj.getCourse_term();
		
		course_credit = achiv_info_dao.query_credit(course_id);
		course_name = achiv_info_dao.query_cname(course_id);
		course_type = achiv_info_dao.query_ctype(course_id);
		score = achiv_info_dao.query_course_score(student_id, course_id);
		if (flag == 2) {
		  			out.println("<tr class='TABLE_TR_02'>");
		  			flag = 1;
		  		}
  		else if (flag == 1) {
  			out.println("<tr class='TABLE_TR_01'>");
  			flag = 2;
  		}
	%> 
  	<td style="padding:5px;">&nbsp;
  	<%
    	if(score < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
   	<%=course_id%></font></td>
   	
    <td style="padding:5px;">&nbsp;
    <%
    	if(score < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=course_name%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(score < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=course_type%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(score < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=course_credit%></font></td>
    
    <td style="padding:5px;">&nbsp;
    <%
    	if(score < 60) {
    		out.print("<font color=red >");
    	}
    	else {
    		out.print("<font color=black >");
    	}
    %>
    <%=score%></font></td>
    
    </tr>
<%
	}
    }
    else {
    	%>
    	<tr align="center" bgcolor="#FFFFFF">
        <td>没有成绩信息！</td>
        </tr>
    	<%
    }
%>
</table>
</div>


</body>
</html>