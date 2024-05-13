<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Class.forName("oracle.jdbc.OracleDriver");
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    String userID = CookieManager.readCookie(request, "userID");
    String userName = CookieManager.readCookie(request, "userName");

    String sql = "SELECT birth from MEMBER WHERE userID = ? AND userName = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, userID);
    preparedStatement.setString(2, userName);

    ResultSet resultSet = preparedStatement.executeQuery();

    String birth = "";
    if (resultSet.next()) {
        birth = resultSet.getString("birth");
    }
%>
<html>
<head>
    <title>Info</title>
</head>
<body>
<h2>UserID : <%= userID %></h2>
<h2>UserName : <%= userName %></h2>
<h2>Birth : <%= birth %></h2>
<h2><a href="logout.jsp">LogOut</a></h2>
<h2><a href="delete-member.jsp">Delete User</a></h2>
</body>
</html>
