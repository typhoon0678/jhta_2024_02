<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie</title>
</head>
<body>
<h1>Cookie</h1>

<%
    Cookie cookie = new Cookie("myCookie", "myValue");
    cookie.setMaxAge(1000 * 60 * 24);
    response.addCookie(cookie);
%>
</body>
</html>
