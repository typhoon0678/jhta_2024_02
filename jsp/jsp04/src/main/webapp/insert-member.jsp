<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Member</title>
</head>
<body>
<h2>Sign Up</h2>
<form action="insert-member-process.jsp" method="post">
    <div>
        <label>
            <span>ID</span>
            <input type="text" placeholder="Write ID" name="userID">
        </label>
        <br>
        <label>
            <span>PW</span>
            <input type="password" placeholder="Write Password" name="userPW">
        </label>
        <br>
        <label>
            <span>NAME</span>
            <input type="text" placeholder="Write Name" name="userName">
        </label>
        <br>
        <label>
            <span>Birth</span>
            <input type="date" name="userBirth">
        </label>
    </div>
    <button type="submit">Sign Up</button>
    <button type="reset">Reset</button>
</form>
</body>
</html>
