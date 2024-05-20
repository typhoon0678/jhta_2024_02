<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.typhoon0678.jsp05.dto.BoardDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board List</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<%

    String requestPage = request.getParameter("page");
    int pageNum = (requestPage != null) ? Integer.parseInt(requestPage) : 1;

    int perPage = 10;

    String start = String.valueOf(perPage * (pageNum - 1) + 1);
    String end = String.valueOf(perPage * pageNum);

    String[] values = {start, end};
    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT * FROM (" +
                    "SELECT rownum as num, b.* FROM (" +
                    "SELECT * FROM BOARD ORDER BY boardNo DESC) b" +
                    ") " +
                    "WHERE num BETWEEN ? AND ?", values);

    jdbcConnectionPool.setResultSet();

    List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

    ResultSet resultSet = jdbcConnectionPool.getResultSet();

    while (resultSet.next()) {
        BoardDto boardDto = new BoardDto(
                resultSet.getInt("boardNo"),
                resultSet.getString("subject"),
                "",
                "",
                resultSet.getString("userName"),
                resultSet.getString("regDate"),
                resultSet.getInt("hit")
        );

        boardDtoList.add(boardDto);
    }

    request.setAttribute("pageNum", pageNum);
    request.setAttribute("boardDtoList", boardDtoList);

    jdbcConnectionPool.close();
%>

<div class="container">
    <h2 class="mt-5 mb-5">LIST</h2>

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">BoardNo</th>
            <th scope="col">Subject</th>
            <th scope="col">UserName</th>
            <th scope="col">RegDate</th>
            <th scope="col">HIT</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="boardDto" items="${boardDtoList}"
                   begin="0"
                   end="9"
                   varStatus="loop">
            <tr onclick="location.href='view.jsp?boardNo=${boardDto.no}'">
                <td>${boardDto.no}
                </td>
                <td>${boardDto.subject}
                </td>
                <td>${boardDto.userName}
                </td>
                <td>${boardDto.regDate}
                </td>
                <td>${boardDto.hit}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav>
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="./list.jsp?page=1" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="boardDto"
                       begin="0"
                       end="9" varStatus="loop">
                <c:if test="${pageNum == loop.count}">
                    <li class="page-item active">
                        <a class="page-link" href="./list.jsp?page=${loop.count}">${loop.count}</a></li>
                </c:if>
                <c:if test="${pageNum != loop.count}">
                    <li class="page-item">
                        <a class="page-link" href="./list.jsp?page=${loop.count}">${loop.count}</a></li>
                </c:if>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="./list.jsp?page=${11}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>

    <div class="mt-5 mb-5"><a href="write.jsp" class="btn btn-primary">Write</a></div>
</div>
</body>
</html>
