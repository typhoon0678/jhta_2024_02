<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<br/>
<%
    String sessionUserID = (String) session.getAttribute("sessionUserID");

    if (sessionUserID != null) {
        out.print("<h2>Welcome " + sessionUserID + "<h2>");
    }
%>
<h1><%= "Hello World!" %></h1>
<a href="pageContent.jsp">Page Content</a>
<a href="second.jsp">Second</a>
<br>
<a href="hello-servlet">Hello Servlet</a>
<a href="cookie.jsp">Cookie</a>
<a href="cookie-read.jsp">Cookie Read</a>
<a href="popup-main.jsp">Pop Up</a>
<a href="login.jsp">Login</a>
</body>
</html>