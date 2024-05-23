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
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <form action="${pageContext.request.contextPath}/member/password-reset" method="post">
        <div class="mb-3">
            <label for="userPW" class="form-label">PASSWORD</label>
            <input type="password" class="form-control" name="userPW" id="userPW" placeholder="Input Password">
        </div>
        <div class="mb-3">
            <label for="userPW2" class="form-label">PASSWORD2</label>
            <input type="password" class="form-control" name="userPW2" id="userPW2" placeholder="Input Password">
            <div class="password-correct text-success"></div>
            <div class="password-incorrect text-warning"></div>
        </div>
        <div>
            <label for="userID" class="form-label"></label>
            <input type="hidden" class="form-control" name="userID" id="userID" value="${param.id}">
        </div>
        <button class="btn btn-primary" id="btn-reset-pw">Reset Password</button>
    </form>
</div>

<script>
    const pwInput = $("#userPW");
    const pw2Input = $("#userPW2");

    pw2Input.on("keyup", function () {
        if (pwInput.val() === pw2Input.val()) {
            $(".password-correct").text("Correct");
            $(".password-incorrect").text("");
        } else {
            $(".password-correct").text("");
            $(".password-incorrect").text("Incorrect");
        }
    });

    $("#btn-reset-pw").on("click", function () {
        if (pwInput.val().trim() === "") {
            alert("Password required");
            pwInput.focus();
            return false;

        } else if (pwInput.val() !== pw2Input.val()) {
            alert("Check Password");
            pwInput.focus();
            return false;

        }
    });
</script>

<c:if test="${not empty requestScope.error}">
    <script>
        window.addEventListener("load", function () {
            alert("${error}");
        });
    </script>
</c:if>

</body>
</html>