<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<%
    String[] values = {userID, userName};

    ServletContext context = request.getServletContext();

    JdbcConnect jdbcConnect = new JdbcConnect(
            context, "SELECT email, postcode, address, address_detail, birth from MEMBER WHERE userID = ? AND userName = ?", values);

    jdbcConnect.setResultSet();
    ResultSet resultSet = jdbcConnect.getResultSet();

    String email = "";
    String postcode = "";
    String address = "";
    String addressDetail = "";
    String birth = "";

    if (resultSet.next()) {
        email = resultSet.getString("email");
        postcode = resultSet.getString("postcode");
        address = resultSet.getString("address");
        addressDetail = resultSet.getString("address_detail");
        birth = resultSet.getString("birth");
    }

    jdbcConnect.close();
%>

<table class="table">
    <thead>
    <tr>
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
    <tr>
        <td><%=userID%>
        </td>
        <td><%=userName%>
        </td>
        <td><%=email%>
        </td>
        <td><%=postcode%>
        </td>
        <td><%=address%>
        </td>
        <td><%=addressDetail%>
        </td>
        <td><%=birth%>
        </td>
    </tr>
    </tbody>
</table>
<button class="btn btn-danger" onclick="location.href='delete-member.jsp'">Delete User</button>

</body>
</html>