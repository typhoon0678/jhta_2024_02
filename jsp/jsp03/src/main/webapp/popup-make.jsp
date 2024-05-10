<%@ page import="com.typhoon0678.jsp03.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String today = request.getParameter("today");
    if (today != null && today.equals("off")) {
        CookieManager.createCookie(response, "oneDayCookie", "off", 60*60*24);
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
