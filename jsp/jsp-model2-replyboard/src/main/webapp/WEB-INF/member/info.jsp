<%--
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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col">UserID</th>
        <th scope="col">UserName</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col">Detail Address</th>
        <th scope="col">UserBirth</th>
        <th scope="col">Profile</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${member.userID}
        </td>
        <td>${member.userName}
        </td>
        <td>${member.email}
        </td>
        <td>${member.address}
        </td>
        <td>${member.detailAddress}
        </td>
        <td>${member.birth}
        </td>
        <td><img src="${member.renameProfile}">
        </td>
    </tr>
    </tbody>
</table>
<button class="btn btn-danger" onclick="location.href='/member/delete'">Delete User</button>

</body>
</html>