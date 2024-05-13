<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Member</title>
</head>
<body>
<form action="delete-member-process.jsp" method="post">
    <div>
        <label>
            <span>Write Your Password</span>
            <br>
            <input type="password" name="userPW">
        </label>
        <button type="submit">Delete</button>
    </div>
</form>

</body>
</html>
