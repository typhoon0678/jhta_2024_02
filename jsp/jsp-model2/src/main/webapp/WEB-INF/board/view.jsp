<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/24/24
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script async charset="utf-8" src="//cdn.embedly.com/widgets/platform.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
    <div class="col-lg-6 px-0">
        <h1 class="display-4 fst-italic">${board.subject}</h1>
        <div class="d-flex justify-content-between">
            <div class="lead mb-0 text-body-emphasis fw-bold">${board.userName}</div>
            <div class="lead mb-0 text-body-emphasis fw-bold">${board.regDate}</div>
            <div class="lead mb-0 text-body-emphasis fw-bold">hit : ${board.hit}</div>
        </div>
        <p class="lead my-3">${board.content}</p>
    </div>
</div>
<a href="${pageContext.request.contextPath}/board/list" class="ms-4 btn btn-secondary">Back</a>

<script>
    document.querySelectorAll('oembed[url]').forEach(element => {
        // Create the <a href="..." class="embedly-card"></a> element that Embedly uses
        // to discover the media.
        const anchor = document.createElement('a');

        anchor.setAttribute('href', element.getAttribute('url'));
        anchor.className = 'embedly-card';

        element.appendChild(anchor);
    });
</script>

</body>
</html>
