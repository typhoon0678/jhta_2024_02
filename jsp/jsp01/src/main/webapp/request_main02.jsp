<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Main 02</title>
</head>
<body>
<%
    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");
    String gender = request.getParameter("gender");
    String monthStr = request.getParameter("month");
    String[] hobbies = request.getParameterValues("hobby");
    String content = request.getParameter("content");

    if (userID == null) userID = "";
    if (userPW == null) userPW = "";
    if (gender == null) gender = "";
    if (hobbies == null) hobbies = new String[0];
    if (content == null) content = "";
    else content = content.replace("\r\n", "<br>");

    int month = (monthStr == null) ? 0 : Integer.parseInt(monthStr);

    out.println("userID : " + userID);
    out.println("<br>");
    out.println("userPW : " + userPW);
    out.println("<br>");
    out.println("gender : " + gender);
    out.println("<br>");
    out.println("month : " + month);
    out.println("<br>");
    out.println("hobby : ");
    for (String hobby : hobbies) {
        out.println(hobby + " ");
    }
    out.println("<br>");
    out.println("content <br>" + content);
%>

<h1>HI <%= userID %></h1>

<hr>
<h2>request Info</h2>
<ul>
    <li> <%= request.getMethod() %></li>
    <li> <%= request.getServerPort() %></li>
</ul>
</body>
</html>
