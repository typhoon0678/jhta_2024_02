<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/22/24
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <form action="${pageContext.request.contextPath}/member/password-reset" method="post">
        <div class="mb-3">
            <label for="userPW" class="form-label">PASSWORD</label>
            <input type="password" class="form-control" name="userPW" id="userPW" placeholder="Input Password">
        </div>
        <div>
            <label for="userID" class="form-label"></label>
            <input type="hidden" class="form-control" name="userID" id="userID" value="${param.id}">
        </div>
        <button class="btn btn-primary">Reset Password</button>
    </form>
</div>

<c:if test="${not empty requestScope.error}">
    <script>
        window.addEventListener("load", function () {
            alert("${error}");
        });
    </script>
</c:if>

</body>
</html>