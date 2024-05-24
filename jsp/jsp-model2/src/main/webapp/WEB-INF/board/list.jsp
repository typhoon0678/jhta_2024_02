<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/24/24
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board List</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <c:forEach var="boardDto" items="${boardList}" varStatus="loop">
        <div class="col" onclick="location.href='/board/view?boardno=${boardDto.boardNo}'">
            <div class="card h-100">
                <c:choose>
                    <c:when test="${fn:contains(boardDto.content, '<img')}">
                        <img src="${fn:substringBefore(fn:substringAfter(boardDto.content, "src=\""), "\"")}"
                             class="card-img-top" alt="...">
                    </c:when>
                    <c:when test="${fn:contains(boardDto.content, '<oembed url=\"https://www.youtube.com/embed/')}">
                        <img src="https://img.youtube.com/vi/${fn:substring(fn:substringAfter(boardDto.content, "https://www.youtube.com/embed/"), 0, 11)}/mqdefault.jpg"
                             class="card-img-top" alt="...">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/images/sample.jpeg" class="card-img-top" alt="...">
                    </c:otherwise>
                </c:choose>
                <div class="card-body">
                    <h5 class="card-title">${boardDto.subject}</h5>
                    <p class="card-text">
                            ${fn:replace(boardDto.content, fn:substringBefore(fn:substringAfter(boardDto.content, "<img"), ">"), "")}
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>

</div>

<c:if test="${not empty member}">
    <a href="${pageContext.request.contextPath}/board/write" type="button" class="m-4 btn btn-primary">Write Board</a>
</c:if>
<c:if test="${empty member}">
    <a href="${pageContext.request.contextPath}/member/login" type="button" class="m-4 btn btn-primary">Write Board</a>
</c:if>

</body>
</html>
