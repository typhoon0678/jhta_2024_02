<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="include/header.jsp"%>

<h1><%= "Hello World!" %>
</h1>
<br/>
<%
    if (!userID.isEmpty() && !userName.isEmpty()) {
        out.println("<h2>Hi, " + userID + "</h2>");
        out.println("<h2>Hello, " + userName + "</h2>");
        out.println("<h2><a href=\"info.jsp\" class=\"btn btn-primary\">Info</a></h2>");
    }
%>
<a href="hello-servlet" class="btn btn-outline-primary">Hello Servlet</a>
</body>
</html>