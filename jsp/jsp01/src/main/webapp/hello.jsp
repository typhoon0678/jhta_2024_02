<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%! String msg = "hello jsp"; %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello jsp</h1>
<%
    out.println(msg);

    String name = request.getParameter("name");
    out.println("name : " + name);
%>
</body>
</html>
