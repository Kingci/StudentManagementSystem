<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>保存成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body>
	<%
		int para = Integer.valueOf(request.getParameter("para"));
		System.out.println("para is " + para);
		switch(para) {
			case 1:
	 %>
			<script language="javascript">
			alert("学生信息导入成功!");
			window.location.href="query.jsp";
			</script>	
	<%
			break;
			case 2:
	%>
			<script language="javascript">
			alert("学生信息导入失败!");
			window.location.href= "exportStudent.xls";
			</script>
	<%
			break;
			case 3:
	%>
			<script language="javascript">
			alert("学生信息导出成功!");
			window.open("http://localhost:8080/StudentManagementSystem/download/exportStudent.xls");
			window.location.href="query.jsp";
			</script>
	<% 
			break;
			}
	 %>
</body>
</html>