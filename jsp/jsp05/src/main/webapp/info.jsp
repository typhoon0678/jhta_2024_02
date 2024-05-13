<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Info</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="include/header.jsp"%>
<%
  String[] values = {userID, userName};

  ServletContext context = request.getServletContext();

  JdbcConnect jdbcConnect = new JdbcConnect(
          context, "SELECT birth from MEMBER WHERE userID = ? AND userName = ?", values);

  jdbcConnect.setResultSet();
  ResultSet resultSet = jdbcConnect.getResultSet();

  String birth = "";
  if (resultSet.next()) {
    birth = resultSet.getString("birth");
  }

  jdbcConnect.close();
%>
<h2>UserID : <%= userID %></h2>
<h2>UserName : <%= userName %></h2>
<h2>Birth : <%= birth %></h2>
<h2><a href="logout-member-process.jsp">LogOut</a></h2>
<h2><a href="delete-member.jsp">Delete User</a></h2>
</body>
</html>