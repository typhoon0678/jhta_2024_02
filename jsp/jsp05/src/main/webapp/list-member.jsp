<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT userno, userid, username, birth FROM MEMBER", new String[0]);

//    JdbcConnect jdbcConnect = new JdbcConnect(
//            context, "SELECT userno, userid, username, birth FROM MEMBER", new String[0]);

    jdbcConnectionPool.setResultSet();
%>

<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="include/header.jsp"%>
<table border="1" width="800px">
    <tbody>
    <tr>
        <th>userNo</th>
        <th>userID</th>
        <th>userName</th>
        <th>userBirth</th>
    </tr>
    <%
        ResultSet resultSet = jdbcConnectionPool.getResultSet();

        while (resultSet.next()) {
            out.print("<tr>");
            out.print("<td>" + resultSet.getString("userNo") + "</td>");
            out.print("<td>" + resultSet.getString("userID") + "</td>");
            out.print("<td>" + resultSet.getString("userName") + "</td>");
            out.print("<td>" + resultSet.getString("birth") + "</td>");
            out.print("</tr>");
        }

        jdbcConnectionPool.close();
    %>
    </tbody>
</table>
</body>
</html>