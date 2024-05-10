<%@ page import="com.typhoon0678.jsp03.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    CookieManager.deleteCookie(response, "oneDayCookie");
%>
</body>
</html>
