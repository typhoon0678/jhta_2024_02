<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <h2 class="mt-5 mb-5">MEMBER LIST</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <%--            <th scope="col">UserNo</th>--%>
                <th scope="col">UserID</th>
                <th scope="col">UserName</th>
                <th scope="col">Email</th>
                <th scope="col">PostCode</th>
                <th scope="col">Address</th>
                <%--            <th scope="col">Detail Address</th>--%>
                <%--            <th scope="col">UserBirth</th>--%>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="memberDto" items="${memberList}" varStatus="loop">
                <tr>
                    <td >${memberDto.userID}</td>
                    <td>${memberDto.userName}</td>
                    <td>${memberDto.email}</td>
                    <td>${memberDto.postcode}</td>
                    <td>${memberDto.address}</td>
                    <td><button class="btn-delete btn btn-danger">Delete</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>
<script>
    $(".btn-delete").on("click", function () {
        const parent = $(this).parent().parent();
        //alert("경고");
        const sendUserID = $(this).closest("tr").find("td:first").text();
        //alert(sendUserID);
        $.ajax({
            url: "/member/delete-admin",
            method: "post",
            data: {
                userID: sendUserID
            },
            success: function (data) {
                console.log(data);
                if (data.isDeleted === true) {
                    alert("Delete Success")
                    parent.fadeOut();
                } else {
                    alert("Delete Failed");
                }
            }
        });
    });

</script>
</body>
</html>
