<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board Detail</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<%
    String boardNo = request.getParameter("boardNo");

    String[] values = {boardNo};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "UPDATE board SET hit = hit + 1 WHERE boardNo = ?", values
    );

    jdbcConnectionPool.getExecuteUpdate();

    jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT subject, content, regdate, hit FROM BOARD WHERE boardNo = ?", values
    );

    jdbcConnectionPool.setResultSet();
    ResultSet resultSet = jdbcConnectionPool.getResultSet();
%>

<div class="container">
    <main class="container">
        <%
            while (resultSet.next()) {
        %>

        <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
            <div class="col-lg-6 px-0">
                <h1 class="display-4 fst-italic"><%=resultSet.getString("subject")%>
                </h1>
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="py-2">RegDate : <%=resultSet.getString("regdate")%>
                        </div>
                        <div class="py-2">Hit : <%=resultSet.getString("hit")%>
                        </div>
                    </div>
                </div>
                <hr>
                <p class="lead my-3"><%=resultSet.getString("content")%>
                </p>
            </div>
        </div>

        <%
            }
            jdbcConnectionPool.close();
        %>
        <a href="/board/list.jsp" class="btn btn-secondary">Back</a>
    </main>
</div>

</body>
</html>
