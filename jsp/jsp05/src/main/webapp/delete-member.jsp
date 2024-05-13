<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Member</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <style>
        html,
        body {
            height: 100%;
        }

        form {
            max-width: 330px;
            padding: 1rem;
        }
    </style>
</head>
<body>
<%@ include file="include/header.jsp"%>
<form class="m-auto" action="delete-member-process.jsp" method="post">
    <div class="form-floating">
        <input type="password" class="form-control" name="userPW" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Password</label>
    </div>

    <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
</form>
</body>
</html>