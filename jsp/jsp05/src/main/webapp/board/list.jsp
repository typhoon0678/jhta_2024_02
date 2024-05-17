<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %><%--
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
    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool("SELECT * FROM BOARD", new String[0]);

    jdbcConnectionPool.setResultSet();
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

        <%
            ResultSet resultSet = jdbcConnectionPool.getResultSet();

            while (resultSet.next()) { %>

        <tr onclick="location.href='view.jsp?boardNo=<%=resultSet.getInt("boardNo")%>'">
            <td><%= resultSet.getInt("boardNo") %>
            </td>
            <td><%= resultSet.getString("subject") %>
            </td>
            <td><%= resultSet.getString("userName") %>
            </td>
            <td><%= resultSet.getString("regDate") %>
            </td>
            <td><%= resultSet.getInt("hit") %>
            </td>
        </tr>
        <% }

            jdbcConnectionPool.close();
        %>
        </tbody>
    </table>

    <div class="mt-5 mb-5"><a href="write.jsp" class="btn btn-primary">Write</a></div>
</div>
</body>
</html>
