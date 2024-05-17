<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/17/24
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<%
    String boardNo = request.getParameter("boardNo");

    String[] values = {boardNo};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT subject, content FROM BOARD WHERE boardNo = ?", values
    );

    jdbcConnectionPool.setResultSet();
    ResultSet resultSet = jdbcConnectionPool.getResultSet();

    String subject = "";
    String content = "";
    while (resultSet.next()) {

        subject = resultSet.getString("subject");
        content = resultSet.getString("content");
    }

    jdbcConnectionPool.close();

%>

<div class="container">
    <form action="edit-process.jsp?boardNo=<%=boardNo%>" method="post">
        <h1 class="h3 mb-3 fw-normal">Writes</h1>

        <div class="form-floating">
            <input type="hidden" class="form-control" name="boardNo" id="floatingInput" placeholder="Write ID" check-id="" value="<%=boardNo%>">
            <label for="floatingInput"></label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="subject" id="floatingSubject" placeholder="Write Subject" value="<%=subject%>">
            <label for="floatingSubject">Subject</label>
        </div>

        <div class="form-floating">
            <textarea class="form-control" name="content" id="floatingContent" placeholder="Write Content"><%=content%></textarea>
            <label for="floatingContent">Content</label>
        </div>

        <div class="d-flex align-items-center">
            <button class="btn btn-primary w-100 py-2 my-4" id="btn-confirm" type="submit">Confirm</button>
            <button class="btn btn-secondary w-100 py-2 my-4" id="btn-reset" type="button">Reset</button>
        </div>
    </form>
</div>
</body>
</html>
