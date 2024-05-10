<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie Read</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            out.println(cookie.getName() + " : " + cookie.getValue());
        }
    }
%>
</body>
</html>
