<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
    <link href="../css/common.css" rel="stylesheet">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<%
    String boardNo = request.getParameter("boardNo");

    String[] values = {boardNo};

    String boardUserID = "";

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "UPDATE board SET hit = hit + 1 WHERE boardNo = ?", values
    );

    jdbcConnectionPool.getExecuteUpdate();

    jdbcConnectionPool.close();

    jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT userID, subject, content, regdate, hit FROM BOARD WHERE boardNo = ?", values
    );

    jdbcConnectionPool.setResultSet();
    ResultSet resultSet = jdbcConnectionPool.getResultSet();

%>

<div class="container mb-5">
    <main class="container">
        <%
            while (resultSet.next()) {

                boardUserID = resultSet.getString("userID");
        %>

        <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary">
            <div class="col-lg-6 px-0">
                <h1 class="display-4 fst-italic"><%=resultSet.getString("subject")%>
                </h1>
                <div>
                    <div class="d-flex justify-content-between">
                        <div class="py-2">RegDate : <%=resultSet.getString("regdate")%>
                        </div>
                        <div class="py-2">Hit : <%=resultSet.getInt("hit")%>
                        </div>
                    </div>
                </div>
                <hr>
                <p class="lead my-3 content"><%=resultSet.getString("content")%>
                </p>
            </div>
        </div>

        <%
            }
            jdbcConnectionPool.close();
        %>
        <div class="d-flex justify-content-between">
            <div>

                <a href="list.jsp" class="btn btn-secondary">Back</a>
                <a href="edit.jsp?boardNo=<%=boardNo%>" class="btn btn-primary"
                        <%= (userID.equals(boardUserID)) ? "" : "hidden"%>>Edit</a>
            </div>
            <div>

                <a href="delete.jsp?boardNo=<%=boardNo%>" class="btn btn-danger"
                        <%= (userID.equals(boardUserID)) ? "" : "hidden"%>>Delete</a>
                <a class="btn btn-danger" id="btn-ajax"
                        <%= (userID.equals(boardUserID)) ? "" : "hidden"%>>Delete-Ajax</a>
                <a class="btn btn-danger" id="btn-modal"
                        <%= (userID.equals(boardUserID)) ? "" : "hidden"%>>Delete Modal</a>
            </div>
        </div>
    </main>
</div>

<div class="modal fade" id="password-modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">PASSWORD</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label for="userPW">PASSWORD</label>
                <input type="password" id="userPW" class="form-control mt-2" name="userPW">
                <input type="hidden" id="board-no" name="no" value="<%=boardNo%>">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="btn-delete-modal">DELETE</button>
            </div>
        </div>
    </div>
</div>
<script>

    const passwordModal = new bootstrap.Modal("#password-modal");

    $("#btn-modal").on("click", function () {
        passwordModal.show();
    });
    $("#btn-delete-modal").on("click", function () {
        const boardNo = $("#board-no").val();
        const userPW = $("#userPW").val();

        $.ajax({
            url: "delete-ajax-popup.jsp",
            data: {
                boardNo: boardNo,
                userPW: userPW
            },
            method: "POST",
            success: function (result) {
                console.log(result);
                if (result.isDelete === "yes") {
                    alert("Deleted");
                    window.location.href = "list.jsp";
                } else {
                    alert("Failed");
                }
            }
        });
    });


    $("#btn-ajax").on("click", function () {
        $.ajax({
            url: "delete-ajax.jsp",
            data: {
                "boardNo": <%=boardNo%>
            },
            method: "GET",
            success: function (result) {
                console.log(result);
                if (result.isDelete === "yes") {
                    alert("Deleted");
                    window.location.href = "list.jsp";
                } else {
                    alert("Failed");
                }
            }
        });
    });
</script>
</body>
</html>
