<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String rememberID = CookieManager.readCookie(request, "rememberID");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login-member-process.jsp" method="post">
    <div>
        <label>
            <span>ID</span>
            <input type="text" placeholder="Write ID" name="userID" value="<%= rememberID %>">
        </label>
        <br>
        <label>
            <span>PW</span>
            <input type="password" placeholder="Write Password" name="userPW">
        </label>
        <br>
        <label>
            <span>Remember ID</span>
            <input type="checkbox" name="isRememberID" value="yes"
                <% if (!rememberID.isEmpty()) out.print("checked"); %>>
        </label>
    </div>
    <button type="submit">Sign Up</button>
</form>
</body>
</html>
