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
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<main class="container">


    <form action="${pageContext.request.contextPath}/board/list" class="row g-3 align-items-center mb-5" method="post">
        <div class="col-2">
            <select class="form-select" aria-label="Default select example" name="search">
                <option value="subject" ${search == "subject" ? "selected" : ""}>제목</option>
                <option value="username" ${search == "username" ? "selected" : ""}>글쓴이</option>
                <option value="content" ${search == "content" ? "selected" : ""}>내용</option>
                <option value="all" ${search == "all" ? "selected" : ""}>제목+내용</option>
            </select>
        </div>
        <div class="col-8">
            <label for="searchWord"></label>
            <input type="text" name="searchWord" id="searchWord" class="form-control" value="${searchWord}">
        </div>
        <div class="col-2">
            <button class="btn btn-primary">Search</button>
        </div>
    </form>

    <div class="mb-4 h2 text-center">
        ${boardCount}건의 Board
    </div>

    <div class="row">
        <c:forEach var="boardDto" items="${boardList}" varStatus="loop">
            <div class="col-md-6">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <strong class="d-inline-block mb-2 text-primary-emphasis">World</strong>
                        <h3 class="mb-0 text-truncate">${boardDto.subject}</h3>
                        <div class="mb-1 text-body-secondary">${boardDto.regDate}</div>
                        <p class="card-text mb-auto text-truncate">${boardDto.content}</p>
                        <a href="${pageContext.request.contextPath}/board/view?no=${boardDto.no}"
                           class="icon-link gap-1 icon-link-hover stretched-link">
                            Continue reading
                            <svg class="bi">
                                <use xlink:href="#chevron-right"></use>
                            </svg>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <form action="${pageContext.request.contextPath}/board/list" method="post" id="form-nav">
        <input type="hidden" name="page" id="input-page" value="${page}">
        <input type="hidden" name="search" value="${search}">
        <input type="hidden" name="searchWord" value="${searchWord}">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link"
                       id="btn-prev-nav"
                       type="button"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link"
                       id="btn-prev"
                       type="button"
                       aria-label="Previous">
                        <span aria-hidden="true">&lt;</span>
                    </a>
                </li>
                <c:forEach var="i" begin="${perPage * navNum + 1}"
                           end="${(perPage * (navNum + 1) <= total) ? perPage * (navNum + 1) : total}">
                    <li class="page-item">
                        <button class="btn-page page-link ${(i == page) ? 'active' : ''}"
                                type="button">
                                ${i}
                        </button>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <button class="page-link"
                            id="btn-next"
                            type="button"
                            href="/board/list"
                            aria-label="Next">
                        <span aria-hidden="true">&gt;</span>
                    </button>
                </li>
                <li class="page-item">
                    <a class="page-link"
                       id="btn-next-nav"
                       type="button"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </form>

    <c:if test="${not empty member}">
        <a href="${pageContext.request.contextPath}/board/write" type="button" class="btn btn-primary">Write
            Board</a>
    </c:if>
    <c:if test="${empty member}">
        <a href="${pageContext.request.contextPath}/member/login" type="button" class="my-4 btn btn-primary">Write
            Board</a>
    </c:if>

</main>

<script>
    $("#btn-prev").on("click", function () {
        $("#input-page").val(${(page > 1) ? page - 1 : 1});
        $("#form-nav").submit();
    });

    $("#btn-next").on("click", function () {
        $("#input-page").val(${(page < total) ? page + 1 : total});
        $("#form-nav").submit();
    });

    $("#btn-prev-nav").on("click", function () {
        $("#input-page").val(${(navNum > 10) ? navNum - 10 : 1});
        $("#form-nav").submit();
    });

    $("#btn-next-nav").on("click", function () {
        $("#input-page").val(${(perPage * (navNum + 1) + 1 <= total) ? perPage * (navNum + 1) + 1: total});
        $("#form-nav").submit();
    });

    $(".btn-page").on("click", function () {
        $("#input-page").val($.trim($(this).text()));
        $("#form-nav").submit();
    });

</script>

</body>
</html>
