<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Main</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    if (name == null) name = "";

    String ageStr = request.getParameter("age");
    int age = (ageStr == null) ? 0 : Integer.parseInt(ageStr);
    
    out.println("name : " + name);
    out.println("<br>");
    out.println("age : " + age);
%>
</body>
</html>
