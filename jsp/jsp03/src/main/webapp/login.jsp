<%@ page import="com.typhoon0678.jsp03.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = CookieManager.readCookie(request, "userID");
    String isChecked = (userID.isEmpty()) ? "" : "checked";
%>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<h2>Login</h2>
<form action="login-process.jsp" method="post">
    <div>
        <label>
            <span>ID</span>
            <input type="text" name="userID" value="<%= userID %>">
        </label>
        <label>
            <input type="checkbox" name="saveID" value="yes" <%= isChecked %>>
            <span>Remember ID</span>
        </label>
    </div>
    <div>
        <label>
            <span>PASSWORD</span>
            <input type="password" name="userPW">
        </label>
    </div>
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
