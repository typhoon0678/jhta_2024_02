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

<main class="container">


    <form action="${pageContext.request.contextPath}/board/search" class="row g-3 align-items-center mb-5" method="post">
        <div class="col-2">
            <select class="form-select" aria-label="Default select example" name="search">
                <option selected value="subject">제목</option>
                <option value="username">글쓴이</option>
                <option value="content">내용</option>
                <option value="all">제목+내용</option>
            </select>
        </div>
        <div class="col-8">
            <label for="searchWord"></label>
            <input type="text" name="searchWord" id="searchWord" class="form-control">
        </div>
        <div class="col-2">
            <button class="btn btn-primary">Search</button>
        </div>
    </form>

    <div class="row">
        <c:forEach var="boardDto" items="${boardList}" varStatus="loop">
            <div class="col-md-6">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <strong class="d-inline-block mb-2 text-primary-emphasis">World</strong>
                        <h3 class="mb-0">${boardDto.subject}</h3>
                        <div class="mb-1 text-body-secondary">${boardDto.regDate}</div>
                        <p class="card-text mb-auto">${boardDto.content}</p>
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

    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/board/list?page=${(navNum > 10) ? navNum - 10 : 1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/board/list?page=${(page > 1) ? page - 1 : 1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&lt;</span>
                </a>
            </li>
            <c:forEach var="i" begin="${perPage * navNum + 1}"
                       end="${(perPage * (navNum + 1) <= total) ? perPage * (navNum + 1) : total}">
                <li class="page-item">
                    <a class="page-link ${(i == page) ? 'active' : ''}"
                       href="${pageContext.request.contextPath}/board/list?page=${i}">
                            ${i}
                    </a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/board/list?page=${(perPage * (navNum + 1) + 1 <= total) ? perPage * (navNum + 1) + 1: total}"
                   aria-label="Next">
                    <span aria-hidden="true">&gt;</span>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/board/list?page=${(perPage * (navNum + 1) + 1 <= total) ? perPage * (navNum + 1) + 1: total}"
                   aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

    <c:if test="${not empty member}">
        <a href="${pageContext.request.contextPath}/board/write" type="button" class="btn btn-primary">Write
            Board</a>
    </c:if>
    <c:if test="${empty member}">
        <a href="${pageContext.request.contextPath}/member/login" type="button" class="my-4 btn btn-primary">Write
            Board</a>
    </c:if>

</main>


</body>
</html>
