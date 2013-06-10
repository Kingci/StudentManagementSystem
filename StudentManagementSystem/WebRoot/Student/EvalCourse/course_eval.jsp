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
				<li >
					<a href="../AchievementInfo/index.jsp" style="padding: 5px 30px 0;">成绩查看</a>
				</li>
				<span class="sep">|</span>				
				<li class="current">
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
<br/><br/><br/><br/>

<form id="form1" method="post" action="student?action=evaluateCourse">
	  	<%
	  	if (account == null || "".equals(account)){
		
		}
		else{
		  	StudentDAO studentDAO = new StudentDAO();
			Collection courInfo = studentDAO.queryCourse(account);
			ChooseCourseTimeForm chooseCourseTimeForm = studentDAO.queryEvalCourseTime(account);
			int id = -1;
			String nameC = "";
			String nameE = "";
			String semester = "";
			int collegeId = -1;
			String year = "";
			
			if (courInfo != null && !courInfo.isEmpty() && chooseCourseTimeForm != null && chooseCourseTimeForm.getState()){
				out.println("<table width='75%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>");
				//out.println("<tr class='TABLE_TH'><td colspan='9' align='center'>课程评估</td></tr>");
				out.println("<tr class='TABLE_TH'><td colspan='9' align='center' >课程评估（开始时间："+chooseCourseTimeForm.getBeginTime()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 结束时间："+chooseCourseTimeForm.getEndTime()+"）</td></tr>");
				out.println("<tr class='TABLE_TR_01'><td>课程号</td><td>课程中文名</td><td>课程英文名</td><td>学期</td><td>学年</td></tr>");
				Iterator it = courInfo.iterator();
				int  i = 1;
				int flag = 2; 
				while(it.hasNext()){
					CourseForm courseForm = (CourseForm)it.next();
					id = courseForm.getId();
					nameC = courseForm.getNameC();
					nameE = courseForm.getNameE();
					semester = courseForm.getSemester();
					collegeId = courseForm.getCollegeId();
					year = courseForm.getYear();
					Collection departInfo = studentDAO.queryDepartment(Integer.toString(collegeId));
					if(departInfo != null && !departInfo.isEmpty()){
						Iterator itD = departInfo.iterator();
					}
			  		if (flag == 2) {
		  				out.println("<tr class='TABLE_TR_02'><td>"+ id +"</td>");
		  				flag = 1;
			  		}
			  		else if (flag == 1) {
			  			out.println("<tr class='TABLE_TR_01'><td>"+ id +"</td>");
			  			flag = 2;
			  		}
			  		out.println("<td>"+ nameC +"</td>");
					out.println("<td>"+ nameE +"</td>");
					out.println("<td>"+ semester +"</td>");
					out.println("<td>"+ year +"</td></tr>");
					
					out.println("<tr><td>对老师授课满意程度</td>" + 
						"<td><input type='radio' " + "name= '" + id + "' value = 1 />满意</td>"+
						"<td><input type='radio' " + "name= '" + id + "' value = 2 />基本满意</td>"+
						"<td><input type='radio' " + "name= '" + id + "' value = 3 />不满意</td>"+
						"<td></td></tr>");
		  		}
		  		if (flag == 1) 
		  			out.println("<tr class='TABLE_TR_01'><td colspan='9' align='center'><input type='submit' name='1' id='12' value='提交' /></td></tr>	</table>");
		  		else if (flag == 2) 
		  			out.println("<tr class='TABLE_TR_02' ><td colspan='9'align='center'><input type='submit' name='1' id='12' value='提交' /></td></tr>	</table>");
			}
			else if(chooseCourseTimeForm == null || !chooseCourseTimeForm.getState()){
				out.println("课程评估尚未开始");
			}
			else 
			{
				out.println("没有选择课程");
				System.out.println("stuInfo is empty!");
			}
	  	}
	  	%>	
	<input type="hidden" name="account" value="<%=account %>"/>
</form>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>