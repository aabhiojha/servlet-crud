<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<br/>
<a href="tasks">List all Tasks</a>
<br>
<a href="task/new">Create new Task</a>
<br>


</body>
</html>