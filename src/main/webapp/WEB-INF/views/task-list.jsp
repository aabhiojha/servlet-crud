<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: abhishek
  Date: 12/31/2025
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task List</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<a href="${pageContext.request.contextPath}/task/new"
   style="display:inline-block;margin-bottom:20px;">
    Add Task
</a>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Created At</th>
    </tr>
    </thead>
    <tbody>
    <%--    <% System.out.println(Arrays.toString(request.getCookies()));%>--%>
    <% System.out.println(request.getAttribute("tasks"));%>
    <tr>
        <%
            List<Map<String, Object>> taskList = (List<Map<String, Object>>) request.getAttribute("tasks");
            for (Map<String, Object> task : taskList) {
        %>
        <td><%= task.get("id") %>
        </td>
        <td><%= task.get("title") %>
        </td>
        <td><%= task.get("description") %>
        </td>
        <td><%= task.get("status") %>
        </td>
        <td><%= task.get("created_at") %>
        </td>
    </tr>
    <br>
    <% } %>
    </tbody>
</table>
</body>
</html>
