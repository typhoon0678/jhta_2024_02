<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    Class.forName("oracle.jdbc.driver.OracleDriver")
    Class.forName("oracle.jdbc.OracleDriver");

    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    System.out.println("db 연결");

    String sql = "INSERT INTO member VALUES ('userID', '1234', 'username')";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    int result = preparedStatement.executeUpdate();
    System.out.println(result);
%>

<html>
<head>
    <title>DB Connect</title>
</head>
<body>
<h1> Connection : <%= connection %></h1>
</body>
</html>
