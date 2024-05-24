<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/24/24
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write Board</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
    <script src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<form action="${pageContext.request.contextPath}/board/write" method="post">

    <input type="hidden" name="userID" value="${member.userID}">
    <div class="mb-3">
        <label for="userName" class="form-label">USER NAME</label>
        <input type="text" class="form-control" id="userName" name="userName"
               value="${member.userName}" readonly>
    </div>
    <div class="mb-3">
        <label for="subject" class="form-label">SUBJECT</label>
        <input type="text" class="form-control" id="subject" placeholder="Write Subject" name="subject">
    </div>

    <div class="mb-3">
        <label for="content" class="form-label">CONTENT</label>
        <textarea name="content" id="content"></textarea>
    </div>
    <div>
        <button class="btn btn-primary" id="btn-sign">CONFIRM</button>
        <button class="btn btn-secondary" type="reset">RESET</button>
    </div>
</form>

<script>
    ClassicEditor
        .create(document.querySelector("#content"), {
            ckfinder: {
                uploadUrl: "../board/upload-image",
            }
        })
        .catch(error => {
            console.error(error);
        });
</script>

</body>
</html>
