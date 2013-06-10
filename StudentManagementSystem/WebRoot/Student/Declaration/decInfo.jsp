<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="com.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.actionForm.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	} 
%>
<%
	StudentDAO studentDAO = new StudentDAO();
	Collection decInfo = studentDAO.declarationInfo(account);
	String proj_name = "";
	String outlay = "";
	String starting_time= "";
	String terminal_time = "";
	String stu_name1 = "";
	String stu_grade1 = "";
	String stu_major1 = "";
	String stu_id1 = "";
	String stu_phonenum1 = "";
	String stu_email1 = "";
	String stu_name2 = "";
	String stu_grade2 = "";
	String stu_major2 = "";
	String stu_id2 = "";
	String stu_phonenum2 = "";
	String stu_email2 = "";
	String stu_name3 = "";
	String stu_grade3 = "";
	String stu_major3 = "";
	String stu_id3 = "";
	String stu_phonenum3 = "";
	String stu_email3 = "";
	String stu_name4 = "";
	String stu_grade4 = "";
	String stu_major4 = "";
	String stu_id4 = "";
	String stu_phonenum4 = "";
	String stu_email4 = "";
	String teacher_name = "";
	String teacher_age = "";
	String teacher_duties = "";
	String teacher_phonenum = "";
	String teacher_email = "";
	String research_area = "";
	String brief_intro = "";
	String proj_alternatives = "";
	
	String citizenship = "";
	String nation = "";
	String name_en = "";
	String gradu_sch = "";
	String pos_code = "";
	String telephone = "";
	String email = "";
	String home_addr = "";
	if (decInfo != null && !decInfo.isEmpty()){
		Iterator it = decInfo.iterator();
		declarationForm decForm = (declarationForm)it.next();
		
		proj_name = decForm.getprojname();
		outlay = decForm.getoutlay();
		starting_time= decForm.getstartime();
		terminal_time = decForm.getterminaltime();
		stu_name1 = decForm.getstuname(1);
		stu_grade1 = decForm.getstugrade(1);
		stu_major1 = decForm.getstumajor(1);
		stu_id1 = decForm.getstuid(1);
		stu_phonenum1 = decForm.getstuphonenum(1);
		stu_email1 = decForm.getstuemail(1);
		stu_name2 = decForm.getstuname(2);
		stu_grade2 = decForm.getstugrade(2);
		stu_major2 = decForm.getstumajor(2);
		stu_id2 = decForm.getstuid(2);
		stu_phonenum2 = decForm.getstuphonenum(2);
		stu_email2 = decForm.getstuemail(2);
		stu_name3 = decForm.getstuname(3);
		stu_grade3 = decForm.getstugrade(3);
		stu_major3 = decForm.getstumajor(3);
		stu_id3 = decForm.getstuid(3);
		stu_phonenum3 = decForm.getstuphonenum(3);
		stu_email3 = decForm.getstuemail(3);
		stu_name4 = decForm.getstuname(4);
		stu_grade4 = decForm.getstugrade(4);
		stu_major4 = decForm.getstumajor(4);
		stu_id4 = decForm.getstuid(4);
		stu_phonenum4 = decForm.getstuphonenum(4);
		stu_email4 = decForm.getstuemail(4);
		teacher_name = decForm.getteachername();
		teacher_age = decForm.getteacherage();
		teacher_duties = decForm.getteacherduties();
		teacher_phonenum = decForm.getteacherphonenum();
		teacher_email = decForm.getteacheremail();
		research_area = decForm.getresearcharea();
		brief_intro = decForm.getbriefintro();
		proj_alternatives = decForm.getprojalter();
	}
	else 
	{
		System.out.println("decInfo is empty!");
	}
 %>
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
				<li >
					<a href="../EvalCourse/index.jsp" style="padding: 5px 30px 0;">课程评估</a>
				</li>
                <span class="sep">|</span>				
	  			<li class="current">
					<a href="../Declaration/index.jsp" style="padding: 5px 30px 0;">申报系统</a>
				</li>				
	 </ul>
	 <br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
</div>
<br/><br /><br /><br/>

<table width='75%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'>
    <td colspan="2">项目名称</td>
    <td colspan="5"><label for="proj_id"></label>
      <label for="proj_name"><%=proj_name%></label></td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td colspan="2">申请经费</td>
    <td width="168"><label for="outlay"><%=outlay%></label></td>
    <td>起始时间</td>
    <td><label for="starting_time"><%=starting_time%></label></td>
    <td>结束时间</td>
    <td><%=terminal_time%></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td width="14" rowspan="5">申请人或申请团队</td>
    <td width="84">姓名</td>
    <td>年级</td>
    <td width="91">专业</td>
    <td width="172">学号</td>
    <td width="73">联系电话</td>
    <td width="173">E-mail</td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td height="40"><%=stu_name1%></td>
    <td><%=stu_grade1%></td>
    <td><%=stu_major1%></td>
    <td><%=stu_id1%></td>
    <td><%=stu_phonenum1%></td>
    <td><%=stu_email1%></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="40"><%=stu_name2%></td>
    <td><%=stu_grade2%></td>
    <td><%=stu_major2%></td>
    <td><%=stu_id2%></td>
    <td><%=stu_phonenum2%></td>
    <td><%=stu_email2%></td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td height="40"><%=stu_name3%></td>
    <td><%=stu_grade3%></td>
    <td><%=stu_major3%></td>
    <td><%=stu_id3%></td>
    <td><%=stu_phonenum3%></td>
    <td><%=stu_email3%></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="40"><%=stu_name4%></td>
    <td><%=stu_grade4%></td>
    <td><%=stu_major4%></td>
    <td><%=stu_id4%></td>
    <td><%=stu_phonenum4%></td>
    <td><%=stu_email4%></td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td rowspan="2">导师</td>
    <td>姓名</td>
    <td>年龄</td>
    <td>职务</td>
    <td>联系电话</td>
    <td>E-mail</td>
    <td>研究方向</td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td><%=teacher_name%></td>
    <td><%=teacher_age%></td>
    <td><%=teacher_duties%></td>
    <td><%=teacher_phonenum%></td>
    <td><%=teacher_email%></td>
    <td><%=research_area%></td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td height="103" colspan="7"><%=brief_intro%></td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="121" colspan="7"><%=proj_alternatives%></td>
  </tr>
</table>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>