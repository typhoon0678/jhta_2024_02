<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/22/24
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Password</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <form action="${pageContext.request.contextPath}/member/password-search" method="post">
        <div class="mb-3">
            <label for="userID" class="form-label">USERID</label>
            <input type="text" class="form-control" name="userID" id="userID" placeholder="Input UserID">
        </div>
        <button class="btn btn-primary">Send "Password Reset" Email</button>
    </form>
</div>

</body>
</html>
