<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%--
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
    <script src="../js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <h2 class="mt-5 mb-5">MEMBER LIST</h2>
    <form action="delete-member-process-all.jsp" method="get">
        <table class="table table-striped">
            <thead>
            <tr>
                <%--            <th scope="col">UserNo</th>--%>
                <th scope="col">UserID</th>
                <th scope="col">UserName</th>
                <th scope="col">Email</th>
                <th scope="col">PostCode</th>
                <%--            <th scope="col">Address</th>--%>
                <%--            <th scope="col">Detail Address</th>--%>
                <%--            <th scope="col">UserBirth</th>--%>
                <th></th>
                <th><label for="check-all"></label><input type="checkbox" id="check-all"></th>
            </tr>
            </thead>
            <tbody>
            <%
                ResultSet resultSet = jdbcConnectionPool.getResultSet();

                while (resultSet.next()) { %>
            <tr>
                <%--                <td><%= resultSet.getInt("userNo") %>
                </td>--%>
                <td><%= resultSet.getString("userID") %>
                </td>
                <td><%= resultSet.getString("userName") %>
                </td>
                <td><%= resultSet.getString("email") %>
                </td>
                <td><%= resultSet.getString("postcode") %>
                </td>
                <%--                <td><%= resultSet.getString("address") %>
                </td>--%>
                <%--                <td><%= resultSet.getString("address_detail") %>
                </td>--%>
                <%--                <td><%= resultSet.getString("birth") %>
                </td>--%>
                <td><a href=\"/member/admin-delete-member-process.jsp?userID=<%=resultSet.getString("userid")%>"
                       class="btn btn-danger">DEL</a>
                    <button class="btn-delete btn btn-danger mx-1" type="button"
                            data-userid="<%=resultSet.getString("userID")%>">AJAX-DEL
                    </button>
                </td>
                <td><input type="checkbox" class="check" name="check"
                           value="<%=resultSet.getString("userNo")%>"></input></td>
            </tr>
            <% }

                jdbcConnectionPool.close();
            %>
            </tbody>
        </table>
        <button type="submit" class="btn-delete-all btn btn-danger">DEL</button>
    </form>
</div>
<script>
    $(".btn-delete").on("click", function () {
        const parent = $(this).parent().parent();
        //alert("경고");
        const sendUserID = $(this).data("userid");
        //alert(sendUserID);
        $.ajax({
            url: "/member/admin-delete-member-ajax-process.jsp",
            data: {
                userID: sendUserID
            },
            success: function (data) {
                console.log(data);
                if (data.isDelete === "yes") {
                    //alert(sendUserID+"님을 탈퇴시겼습니다.");
                    //location.reload();
                    //parent.remove();
                    parent.fadeOut();
                }
            }
        });
    });

    $("#check-all").on("change", function () {
        if ($(this).is(":checked")) {
            $(".check").prop("checked", true);
        } else {
            $(".check").prop("checked", false);
        }
    });

    $(".btn-delete-all").on("click", function () {
        return confirm("삭제하시겠습니까?");
    });
</script>
</body>
</html>
