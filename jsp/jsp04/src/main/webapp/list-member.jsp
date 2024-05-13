<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Class.forName("oracle.jdbc.OracleDriver");
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    String sql = "SELECT userno, userid, username, birth FROM MEMBER";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="800px">
    <tbody>
    <tr>
        <th>userNo</th>
        <th>userID</th>
        <th>userName</th>
        <th>userBirth</th>
    </tr>
    <%
        while (resultSet.next()) {
            out.print("<tr>");
            out.print("<td>" + resultSet.getString("userNo") + "</td>");
            out.print("<td>" + resultSet.getString("userID") + "</td>");
            out.print("<td>" + resultSet.getString("userName") + "</td>");
            out.print("<td>" + resultSet.getString("birth") + "</td>");
            out.print("</tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
