<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/30/24
  Time: 9:16 AM
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

    <form action="${pageContext.request.contextPath}/board/list" class="row g-3 align-items-center mb-5" method="get">
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


    <h2 class="d-flex justify-content-center my-4">Total : ${total}</h2>

    <form action="${pageContext.request.contextPath}/board/delete-all" method="post" id="form-delete-all">
        <table class="table table-hover">
            <thead>
            <tr class="row">
                <th class="col-1">NO</th>
                <th class="col-2">Subject</th>
                <th class="col">Content</th>
                <th class="col-2">UserName</th>
                <th class="col-1">Hit</th>
                <th class="col-2">RegDate</th>
                <c:if test="${not empty member and member.grade.getLabel() eq 'admin'}">
                    <td class="col-1">
                        <label for="check-all"></label>
                        <input type="checkbox" id="check-all">
                    </td>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${boardList}" var="board" varStatus="loop">
                <tr class="row">
                    <td class="col-1"
                        onclick="location.href='/board/view?boardNo=${board.no}'">${total - perPage * (page - 1) - loop.index}</td>
                    <td class="col-2" onclick="location.href='/board/view?boardNo=${board.no}'">${board.subject}</td>
                    <td class="col text-truncate"
                        onclick="location.href='/board/view?boardNo=${board.no}'">${board.content}</td>
                    <td class="col-2" onclick="location.href='/board/view?boardNo=${board.no}'">${board.userName}</td>
                    <td class="col-1" onclick="location.href='/board/view?boardNo=${board.no}'">${board.hit}</td>
                    <td class="col-2" onclick="location.href='/board/view?boardNo=${board.no}'">${board.regDate}</td>
                    <c:if test="${not empty member and member.grade.getLabel() eq 'admin'}">
                        <td class="col-1">
                            <label>
                                <input type="checkbox" name="check" class="check" value="${board.no}">
                            </label>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>


    <nav aria-label="...">
        <ul class="pagination pagination-sm">
            <c:if test="${not empty member and member.grade.getLabel() eq 'admin'}">
                <c:forEach var="index" begin="1" end="${(total / 10) + (1 - (total / 10) % 1) % 1}">
                    <li>
                        <form class="page-item ${(page == index) ? "active" : ""}" aria-current="page"
                              action="${pageContext.request.contextPath}/board/list" method="get">
                            <input type="hidden" name="search" value="${search}">
                            <input type="hidden" name="searchWord" value="${searchWord}">
                            <input type="hidden" name="page" value="${index}">
                            <button class="page-link">${index}</button>
                        </form>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>

    <div class="mt-5 mb-5 d-flex justify-content-end">
        <c:choose>
            <c:when test="${not empty member}">
                <a href="${pageContext.request.contextPath}/board/write" type="button" class="btn btn-primary me-4">
                    Write Board
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/member/login" type="button" class="btn btn-primary me-4">
                    Write Board
                </a>
            </c:otherwise>
        </c:choose>
        <c:if test="${not empty member and member.grade.getLabel() eq 'admin'}">
            <button class="btn btn-danger" id="btn-delete-all">DELETE-ALL</button>
        </c:if>
    </div>

</main>

<script>

    $("#check-all").on("change", function () {
        if ($(this).prop("checked")) {
            $(".check").prop("checked", true);
        } else {
            $(".check").prop("checked", false);
        }
    });

    $("#btn-delete-all").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/board/delete-all",
            type: "POST",
            data: $("#form-delete-all").serialize(),
            success: function (res) {
                alert(res.result + " Board Deleted");
                location.reload();
            },
            error: function (res) {
                alert(res);
            }
        });
    });
</script>

</body>
</html>
