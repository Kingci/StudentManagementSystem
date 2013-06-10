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

<script language="javascript">
	function check(form){
		/*if (form.proj_name.value==""){
			alert("请输入项目名称！");
			form.proj_name.focus();
			return false;
		}*/
		if (form.outlay.value==""){
			alert("请输入经费！");
			form.outlay.focus();
			return false;
		}
		if (form.starting_time.value==""){
			alert("请输入起始时间！");
			form.starting_time.focus();
			return false;
		}
		if (form.terminal_time.value==""){
			alert("请输入结束时间！");
			form.terminal_time.focus();
			return false;
		}
		if (form.stu_name1.value==""){
			alert("请输入学生信息！");
			form.stu_name1.focus();
			return false;
		}
		if (form.stu_grade1.value==""){
			alert("请输入学生信息！");
			form.stu_grade1.focus();
			return false;
		}
		if (form.stu_major1.value==""){
			alert("请输入学生信息！");
			form.stu_major1.focus();
			return false;
		}
		if (form.stu_id1.value==""){
			alert("请输入学生信息！");
			form.stu_id1.focus();
			return false;
		}
		if (form.stu_phonenum1.value==""){
			alert("请输入学生信息！");
			form.stu_phonenum1.focus();
			return false;
		}
		if (form.stu_email1.value==""){
			alert("请输入学生信息！");
			form.stu_email1.focus();
			return false;
		}
		if (form.teacher_name.value==""){
			alert("请输入教师信息！");
			form.teacher_name.focus();
			return false;
		}
		if (form.teacher_age.value==""){
			alert("请输入教师信息！");
			form.teacher_age.focus();
			return false;
		}
		if (form.teacher_duties.value==""){
			alert("请输入教师信息！");
			form.teacher_duties.focus();
			return false;
		}
		if (form.teacher_phonenum.value==""){
			alert("请输入教师信息！");
			form.teacher_phonenum.focus();
			return false;
		}
		if (form.teacher_email.value==""){
			alert("请输入教师信息！");
			form.teacher_email.focus();
			return false;
		}
		if (form.research_area.value==""){
			alert("请输入教师信息！");
			form.research_area.focus();
			return false;
		}
		if (form.brief_intro.value==""){
			alert("请输入项目简介！");
			form.brief_intro.focus();
			return false;
		}
		if (form.proj_alter.value==""){
			alert("请输入项目方案！");
			form.proj_alter.focus();
			return false;
		}
	}
</script>
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

<form id="form1" name="form1" method="post" action="student?action=onlinedeclare">
<table width='75%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <%
  	AcdemicDAO acdemicDAO = new AcdemicDAO();
	Collection ProjInfo = acdemicDAO.queryProj(null);
	boolean selected;
	ArrayList<String> name = new ArrayList<String>();
	
	if (ProjInfo != null && !ProjInfo.isEmpty()){
		Iterator it = ProjInfo.iterator();
		while(it.hasNext()){
			ProjForm projForm = (ProjForm)it.next();
			selected = projForm.getSelected();
			if (selected)
				name.add(projForm.getName());
  		}
	}
	else 
	{
		out.println("<tr><td>当前无已创建项目！</td></tr>");
		System.out.println("ProjInfo is empty!");
	}
  	
  %>
 <tr class='TABLE_TH'>
    <td colspan="2">项目名称</td>
    <td colspan="5">
      <label for="proj_name2"></label>
      <select name="proj_name2" id="proj_name2">
      <% 
      	for (int i = 0;i < name.size();i++){
      		out.println("<option>" + name.get(i) + "</option>");
      	}
      %>
      </select>
    </td>
  </tr>
 <tr class='TABLE_TR_01'>
    <td colspan="2">申请经费</td>
    <td width="168">
      <label for="outlay"></label>
      <input type="text" name="outlay" id="outlay" />
    </td>
    <td>起始时间</td>
    <td>
      <label for="starting_time"></label>
      <input type="text" name="starting_time" id="starting_time" />
    </td>
    <td>结束时间</td>
    <td>
      <label for="terminal_time"></label>
      <input type="text" name="terminal_time" id="terminal_time" />
    </td>
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
    <td height="44">
      <label for="stu_name1"></label>
      <input type="text" name="stu_name1" id="stu_name1" />
    </td>
    <td>
      <label for="stu_grade1"></label>
      <input type="text" name="stu_grade1" id="stu_grade1" />
    </td>
    <td>
      <label for="stu_major1"></label>
      <input type="text" name="stu_major1" id="stu_major1" />
    </td>
    <td>
      <label for="stu_id1"></label>
      <input type="text" name="stu_id1" id="stu_id1" />
    </td>
    <td>
      <label for="stu_phonenum1"></label>
      <input type="text" name="stu_phonenum1" id="stu_phonenum1" />
    </td>
    <td>
      <label for="stu_email1"></label>
      <input type="text" name="stu_email1" id="stu_email1" />
    </td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="49">
      <label for="stu_name2"></label>
      <input type="text" name="stu_name2" id="stu_name2" />
    </td>
    <td>
      <label for="stu_grade2"></label>
      <input type="text" name="stu_grade2" id="stu_grade2" />
    </td>
    <td>
      <label for="stu_major2"></label>
      <input type="text" name="stu_major2" id="stu_major2" />
    </td>
    <td>
      <label for="stu_id2"></label>
      <input type="text" name="stu_id2" id="stu_id2" />
    </td>
    <td>
      <label for="stu_phonenum2"></label>
      <input type="text" name="stu_phonenum2" id="stu_phonenum2" />
    </td>
    <td>
      <label for="stu_email2"></label>
      <input type="text" name="stu_email2" id="stu_email2" />
    </td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td height="46">
      <label for="stu_name3"></label>
      <input type="text" name="stu_name3" id="stu_name3" />
    </td>
    <td>
      <label for="stu_grade3"></label>
      <input type="text" name="stu_grade3" id="stu_grade3" />
    </td>
    <td>
      <label for="stu_major3"></label>
      <input type="text" name="stu_major3" id="stu_major3" />
    </td>
    <td>
      <label for="stu_id3"></label>
      <input type="text" name="stu_id3" id="stu_id3" />
    </td>
    <td>
      <label for="stu_phonenum3"></label>
      <input type="text" name="stu_phonenum3" id="stu_phonenum3" />
    </td>
    <td>
      <label for="stu_email3"></label>
      <input type="text" name="stu_email3" id="stu_email3" />
    </td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="44">
      <label for="stu_name4"></label>
      <input type="text" name="stu_name4" id="stu_name4" />
    </td>
    <td>
      <label for="stu_grade4"></label>
      <input type="text" name="stu_grade4" id="stu_grade4" />
    </td>
    <td>
      <label for="stu_major4"></label>
      <input type="text" name="stu_major4" id="stu_major4" />
    </td>
    <td>
      <label for="stu_id4"></label>
      <input type="text" name="stu_id4" id="stu_id4" />
    </td>
    <td>
      <label for="stu_phonenum4"></label>
      <input type="text" name="stu_phonenum4" id="stu_phonenum4" />
    </td>
    <td>
      <label for="stu_email4"></label>
      <input type="text" name="stu_email4" id="stu_email4" />
    </td>
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
    <td>
      <label for="teacher_name"></label>
      <input type="text" name="teacher_name" id="teacher_name" />
    </td>
    <td>
      <label for="teacher_age"></label>
      <input type="text" name="teacher_age" id="teacher_age" />
    </td>
    <td>
      <label for="teacher_duties"></label>
      <input type="text" name="teacher_duties" id="teacher_duties" />
    </td>
    <td>
      <label for="teacher_phonenum"></label>
      <input type="text" name="teacher_phonenum" id="teacher_phonenum" />
    </td>
    <td>
      <label for="teacher_email"></label>
      <input type="text" name="teacher_email" id="teacher_email" />
    </td>
    <td>
      <label for="research_area"></label>
      <input type="text" name="research_area" id="research_area" />
    </td>
  </tr>
  <tr class='TABLE_TR_01'>
    <td height="103" colspan="7">
      <label for="brief_intro"></label>
      <input type="text" name="brief_intro" id="brief_intro" />
    </td>
  </tr>
  <tr class='TABLE_TR_02'>
    <td height="121" colspan="7">
      <label for="proj_alternatives"></label>
      <input type="text" name="proj_alternatives" id="proj_alternatives" />
    </td>
  </tr>
  <tr class='TABLE_TR_01'><td colspan='7' align='center'><input type="submit" name="save" id="save" value="提交" onclick="return check(form1)"/></td></tr>
</table>


<input type="hidden" name="account" value="<%=account %>"/>
</form>
<script type="text/javascript" src="../../JS/flash.js"></script>
</body>
</html>