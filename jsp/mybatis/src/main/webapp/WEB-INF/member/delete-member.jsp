<%--
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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
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
<%@ include file="../include/header.jsp" %>
<form class="m-auto" action="${pageContext.request.contextPath}/member/delete" method="post">
    <div class="form-floating">
        <input type="password" class="form-control" name="userPW" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Password</label>
    </div>

    <button class="btn btn-danger w-100 py-2" type="submit">Delete User</button>
</form>

<c:if test="${not empty requestScope.error}">
    <script>
        window.addEventListener("load", function () {
            alert("${error}");
        });
    </script>
</c:if>
</body>
</html>