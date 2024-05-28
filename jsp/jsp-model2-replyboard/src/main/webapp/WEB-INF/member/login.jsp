<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<style>
    html,
    body {
        height: 100%;
    }

    .form-signin {
        max-width: 480px;
        padding: 1rem;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="text"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<body class="bg-body-tertiary">
<%@ include file="../include/header.jsp" %>

<main class="form-signin w-100 pt-5 m-auto">
    <form action="${pageContext.request.contextPath}/member/login" method="post">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" name="userID" id="floatingInput" placeholder="Write ID"
                   value="${cookie.rememberID.value}">
            <label for="floatingInput">User ID</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="userPW" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" name="isRememberID" id="flexCheckDefault" value="yes"
            ${(not empty cookie.rememberID) ? 'checked' : ''}>
            <label class="form-check-label" for="flexCheckDefault">
                Remember ID
            </label>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
    <a href="${pageContext.request.contextPath}/member/password-search" class="btn btn-secondary">Forget Password</a>
</main>

<c:if test="${not empty requestScope.error}">
    <script>
        window.addEventListener("load", function () {
            alert("${error}");
        });
    </script>
</c:if>
</body>
</html>