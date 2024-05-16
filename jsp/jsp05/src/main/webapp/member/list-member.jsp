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
            "SELECT userno, userid, username, email, postcode, address, address_detail, birth FROM MEMBER", new String[0]);

//    JdbcConnect jdbcConnect = new JdbcConnect(
//            context, "SELECT userno, userid, username, birth FROM MEMBER", new String[0]);

    jdbcConnectionPool.setResultSet();
%>

<html>
<head>
    <title>Title</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col">UserNo</th>
        <th scope="col">UserID</th>
        <th scope="col">UserName</th>
        <th scope="col">Email</th>
        <th scope="col">PostCode</th>
        <th scope="col">Address</th>
        <th scope="col">Detail Address</th>
        <th scope="col">UserBirth</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet resultSet = jdbcConnectionPool.getResultSet();

        while (resultSet.next()) {
            out.print("<tr>");
            out.print("<th scope=\"row\">" + resultSet.getString("userNo") + "</td>");
            out.print("<td>" + resultSet.getString("userID") + "</td>");
            out.print("<td>" + resultSet.getString("userName") + "</td>");
            out.print("<td>" + resultSet.getString("email") + "</td>");
            out.print("<td>" + resultSet.getString("postcode") + "</td>");
            out.print("<td>" + resultSet.getString("address") + "</td>");
            out.print("<td>" + resultSet.getString("address_detail") + "</td>");
            out.print("<td>" + resultSet.getString("birth") + "</td>");
            out.print("</tr>");
        }

        jdbcConnectionPool.close();
    %>
    </tbody>
</table>
