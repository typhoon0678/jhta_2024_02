<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HELLO</title>
</head>
<%
    String id = (String) request.getAttribute("userID");
%>

<body>
  <h1>HELLO <%= id %></h1>
</body>
</html>
