<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/30/24
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<div class="container">
    <h2 class="mt-5 mb-5">글쓰기</h2>
    <form action="${pageContext.request.contextPath}/board/write" method="post">
        <input type="hidden" name="userID" value="${member.userID}">
        <input type="hidden" name="userName" value="${member.userName}">
        <div class="mb-3">
            <label for="subject" class="form-label">SUBJECT</label>
            <input type="text" class="form-control" id="subject" placeholder="제목을 쓰세요" name="subject">
        </div>

        <div class="mb-3">
            <label for="content" class="form-label">CONTENT</label>
            <textarea name="content" id="content" placeholder="내용을 입력하세요." rows="8" class="form-control"></textarea>
        </div>
        <div>
            <button class="btn btn-primary" id="btn-sign" type="submit">CONFIRM</button>
            <button class="btn btn-secondary" type="reset">RESET</button>
        </div>
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
