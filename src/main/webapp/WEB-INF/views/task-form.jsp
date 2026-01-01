<%--
  Created by IntelliJ IDEA.
  User: abhishek
  Date: 12/31/2025
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<form action="${pageContext.request.contextPath}/tasks" , method="post" , class="task-form">

    <label for="title">Title</label>
    <input type="text" id="title" name="title" required/>

    <label for="description">Description</label>
    <textarea id="description" name="description" rows="4"></textarea>

    <label for="status">Status</label>
    <select id="status" name="status">
        <option value="pending">Pending</option>
        <option value="in_progress">In Progress</option>
        <option value="done">Done</option>
    </select>

    <button type="submit">Create Task</button>
</form>

</body>
</html>
