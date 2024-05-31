<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/29/24
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Board View</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<main class="container">
    <article class="blog-post my-5">
        <h2 class="display-5 link-body-emphasis mb-1">${board.subject}</h2>
        <p class="blog-post-meta">${board.regDate} by <strong>${board.userName}</strong></p>

        <p>${board.content}</p>

        <button class="btn btn-secondary" id="btn-reply-article">Reply</button>
    </article>
</main>

<script>
    $("#btn-reply-article").on("click", function () {

    });

    function getFormText(reLevel) {
        return `
        <div id="form-reply">
        <form action="${pageContext.request.contextPath}/board/reply" method="post">
            <input type="hidden" id="userID" name="userID" value="${member.userID}">
            <input type="hidden" id="userName" name="userName" value="${member.userName}">
            <input type="hidden" id="reGroup" name="reGroup" value="${board.reGroup}">
            <input type="hidden" id="reLevel" name="reLevel" value="` + reLevel + `">
            <div class="mt-1">
                <label for="subject" class="form-label"></label>
                <input type="text" class="form-control" id="subject" placeholder="제목을 쓰세요" name="subject">
            </div>

            <div class="mb-1">
                <label for="content" class="form-label"></label>
                <textarea name="content" id="content" placeholder="내용을 입력하세요." rows="2" class="form-control"></textarea>
            </div>
            <div>
                <button class="btn btn-primary" id="btn-reply" type="button" onclick="btnReply()">CONFIRM</button>
                <button class="btn btn-secondary" type="reset">RESET</button>
            </div>
        </form>
    </div>
        `;
    }


</script>

</body>
</html>
