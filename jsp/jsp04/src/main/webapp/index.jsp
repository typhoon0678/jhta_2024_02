<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    String userID = CookieManager.readCookie(request, "userID");
    String userName = CookieManager.readCookie(request, "userName");

    if (!userID.isEmpty() && !userName.isEmpty()) {
        out.println("<h2>Hi, " + userID + "</h2>");
        out.println("<h2>Hello, " + userName + "</h2>");
        out.println("<h2><a href=\"info.jsp\">Info</a></h2>");
    }
%>
<h1><%= "Hello World!" %>
</h1>
<%
    if (!userID.isEmpty() && !userName.isEmpty()) {
        out.print("<a href=\"logout.jsp\">LogOut</a>");
    } else {
        out.print("<a href=\"login.jsp\">Login</a>");
    }
%>
<br/>
<br />
<a href="hello-servlet">Hello Servlet</a>
<a href="db-connect.jsp">DB Connect</a>
<a href="insert-member.jsp">Insert Member</a>
<a href="list-member.jsp">List Member</a>
</body>
</html>