<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html>
<head>
<title>导入成功</title>
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
			alert("成绩导入成功!");
			window.location.href="importGrades.jsp";
			</script>	
	<%
			break;
			case 2:
	%>
			<script language="javascript">
			alert("成绩导入失败!");
			window.location.href="importGrades.jsp";
			</script>
	<%
			break;
			}
	 %>
</body>
</html>