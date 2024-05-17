<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/17/24
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<%
    String boardNo = request.getParameter("boardNo");
    System.out.println(boardNo);
%>
<main class="container">
    <form action="delete-process.jsp?boardNo=<%=boardNo%>" method="post">
        <p>삭제를 위해 비밀번호를 입력해주세요</p>
        <label id="password">
            <input name="password" type="password" placeholder="password">
        </label>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</main>
</body>
</html>
