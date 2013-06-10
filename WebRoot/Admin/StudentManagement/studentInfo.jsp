<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.dao.*" %>
<%@ page import="com.actionForm.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%String account = (String)session.getAttribute("Account");
	String accountType = (String)session.getAttribute("AccountType");
	if (account == null || "".equals(account)){
		response.sendRedirect("../../index.jsp");
	} 
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
<script language="javascript">
		var xmlhttp;
		function query(){
			var college_n = document.getElementById("college_name").value;
			var entry_t = document.getElementById("entr_time").value;
			if(window.XMLHttpRequest){  
		      //针对FireFox,Mozillar,Opera,Safari,IE7,IE8  
			    xmlhttp = new XMLHttpRequest();  
			         
			       //对某些特定版本的mozillar浏览器的bug进行修正  
			    if(xmlhttp.overrideMineType){  
			          xmlhttp.overrideMineType("text/xml");  
			    }  
		    }
		    else if(window.ActiveXObject){  
		       //针对IE5，IE5.5，IE6  
		         
		       //两个可以用于创建XMLHTTPRequest对象的控件名称。保存在一个JS数组中。  
		       var activexName = ["MSXML2.XMLHTTP","Microsoft.XMLHTTP"];  
		       for(var i = 0; i<activeName.length; i++){  
		           //取出一个控件名进行创建，如果成功就终止循环  
		           try{  
		              xmlhttp = new ActiveXObject(activexName[i]);  
		              break;  
		           }catch(e){}  
		       }         
         
    		}  
		    if(xmlhttp){  
		       alert("XMLHttpRequest对象创建成功！"); 
		       //return ;  
		    }else{  
		       alert("XMLHttpRequest对象创建失败！");  
		       return;
		    } 
 
      
    		//设置连接信息(请求方式，请求的url,true表示异步方式交互)  
    		xmlhttp.open("GET","admin?action=queryStudentInfo", true);  
    		//注册回调函数,只写函数名，不能写括号，写括号表示调用函数。  
    		xmlhttp.onreadystatechange = callback; 
        	xmlhttp.setCharacterEncoding("utf-8"); 
    		//发送数据，开始和服务器进行交互。  
    		xmlhttp.send(null);  
		}
		
		//回调函数  
	function callback(){  
	   //判断对象的状态是否交互完成  
	   if(xmlhttp.readyState == 4){  
	     
	      //判断http的交互是否成功  
	      if(xmlhttp.status == 200){  	        
	         //获取服务器端返回的数据（文本）  
	         var resbonseText = xmlhttp.responseText;     
	         //将数据显示在页面上  
	         alert("hello");
	         var divNode = document.getElementById("result");       
	         //设置元素节点中的html内容  
	         divNode.innerHTML = responseText;  
      		}   
   		}  
	} 
</script>
</head>

<body>
<%
	AdminDAO dao = new AdminDAO();
	ArrayList<String> college = dao.collegeQuery();
	ArrayList<String> entry = dao.entryTimeQuery();
	
 %>
<div id="Header">
	  <ul  id="1" class="MenuBar">	 
      	<li>
			<a href="../index.jsp" style="padding: 5px 30px 0;">首页</a>
		</li> 
        <span class="sep">|</span>	
		<li class="current">
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
		<li>
			<a href="../NotificationManagement/index.jsp" style="padding: 5px 30px 0;">通知管理</a>
		</li>
	 </ul>
	<br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;欢迎您：<font color="#0000ff"><%=account%></font> &nbsp;当前身份：<font color="#0000ff"><%=accountType%></font>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../Images/Icon_Exit.gif'/><a href="../../logout.jsp">退出</a>
	</div>
	
</div>
<br/><br /><br /><br/>


<form id="form1" name="form1" method="post" action="admin?action=queryStudentInfo">
<table width="592" height="43" align='center'>
  <tr>
    <td width="68" height="16">院系名称：</td>
    <td width="217"><label for="college_name"></label>
      <select name="college_name" id="college_name" style="width:200px;height:auto">
      <%
      		for (int i = 0; i < college.size(); i ++){
      			out.println("<option value=\"" + college.get(i) + "\">" + college.get(i) + "</option>");
      		}	
      %>
      </select></td>
    <td width="70">入学年份：</td>
    <td width="144"><label for="entr_time"></label>
      <select name="entr_time" id="entr_time" style="height:auto;width:100px">
    	<%
    		for (int i = 0; i < entry.size(); i ++){
      			 out.println("<option value=\"" + entry.get(i) + "\">" + entry.get(i) + "</option>");
      		}	
    	 %>
      </select></td>
    <td width="69"><input type="submit" name="submit" id="submit" value="提交" "/></td>
  </tr>
</table>
</br>

</form>
<%
	String col = request.getParameter("college_name");
	String entr = request.getParameter("entr_time");
	System.out.println("!!" + col + entr);
	if (col != "" && entr != "" && col != null && entr != null) {	
		ArrayList<StudentForm> al = dao.queryStudentInfo(col, entr);
	
 %>
<table width='75%'  class='TABLE_BODY' bordercolor='777777' border='1' style='border-color:#777777;border-collapse:collapse' align='center'>
  <tr class='TABLE_TH'>
  	<td>编号</td>
    <td>学号</td>
    <td>中文名</td>
    <td>出生日期</td>
    <td>性别</td>
    <td>院系名称</td>
    <td>专业名称</td>
    <td>学制</td>
    <td>身份证号</td>
    <td>入学年份</td>
    <td>国籍</td>
    <td>民族</td>
    <td>电话号码</td>
    <td>家庭住址</td>
    <td>毕业院校</td>
    <td>邮箱地址</td>
    <td>邮编</td>
    </tr>
    <%
    	int flag = 2;
    	for (int i = 0; i < al.size(); i ++) {
    		if (flag == 2) {
		  			out.println("<tr class='TABLE_TR_02'>");
		  			flag = 1;
	  		}
	  		else if (flag == 1) {
	  			out.println("<tr class='TABLE_TR_01'>");
	  			flag = 2;
	  		}
    		out.println(
    			"<td>" + (i + 1) + "</td>" +
			    "<td>" + al.get(i).getStuNum() + "</td>" +
			    "<td>" + al.get(i).getNameCh() + "</td>" +
			    "<td>" + al.get(i).getBirthTime() + "</td>" +
			    "<td>" + al.get(i).getGender() + "</td>" +
			    "<td>" + al.get(i).getCollegeNum() + "</td>" +
			    "<td>" + al.get(i).getMajorNum() + "</td>" +
			    "<td>" + al.get(i).getSchLength() + "</td>" +
			    "<td>" + al.get(i).getIdNum() + "</td>" +
			    "<td>" + al.get(i).getEntrTime() + "</td>" +
			    "<td>" + al.get(i).getCitizenShip() + "</td>"  +
			    "<td>" + al.get(i).getNation() + "</td>" +
			    "<td>" + al.get(i).getTelephone() + "</td>" +
			    "<td>" + al.get(i).getHomeAddr() + "</td>" +
			    "<td>" + al.get(i).getGraduSch() + "</td>" +
			    "<td>" + al.get(i).getEmail() + "</td>" +
			    "<td>" + al.get(i).getPosCode() + "</td>" +
			    "</tr>");
    	}
     %>

</table>
<%
}
 %>
<p>
  <script type="text/javascript" src="../../JS/flash.js"></script>
</p>
<p>&nbsp; </p>
</body>
</html>