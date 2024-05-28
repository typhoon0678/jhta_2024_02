<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/24/24
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@include file="../include/header.jsp" %>

<main class="container">
    <div class="p-4 p-md-5 mb-4 rounded text-body-emphasis bg-body-secondary" id="div-board">
        <div class="col-lg-6 px-0">
            <h1 class="display-4 fst-italic">${board.subject}</h1>
            <div class="d-flex justify-content-between">
                <div class="lead mb-0 text-body-emphasis fw-bold">${board.userName}</div>
                <div class="lead mb-0 text-body-emphasis fw-bold">${board.regDate}</div>
                <div class="lead mb-0 text-body-emphasis fw-bold">hit : ${board.hit}</div>
            </div>
            <p class="lead my-3">${board.content}</p>
        </div>
    </div>


    <table class="table table-hover">
        <thead>
        <tr>
            <th></th>
            <th>subject</th>
            <th>content</th>
            <th>regDate</th>
            <th>reGroup</th>
            <th>reLevel</th>
            <th>reStep</th>
        </tr>
        </thead>
        <tbody id="rep-body">
        <c:forEach var="rep" items="${reply}" varStatus="loop">
            <tr id="rep">
                <c:if test="${rep.reStep <= 1}">
                    <td style="width: 16px;"></td>
                </c:if>
                <c:if test="${rep.reStep > 1}">
                    <td style="width: 16px;">&#8618;</td>
                </c:if>

                <td>${rep.subject}</td>
                <td>${rep.content}</td>
                <td>${rep.regDate}</td>
                <td>${rep.reGroup}</td>
                <td id="td-reLevel">${rep.reLevel}</td>
                <td id="td-reStep">${rep.reStep}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <a href="${pageContext.request.contextPath}/board/list" class="btn btn-secondary">Back</a>

</main>

<script>
    function getFormText(reLevel) {
        return `
        <div id="form-reply">
        <form action="${pageContext.request.contextPath}/board/reply" method="post">
            <input type="hidden" id="userID" name="userID" value="${member.userID}">
            <input type="hidden" id="userName" name="userName" value="${member.userName}">
            <input type="hidden" id="reGroup" name="reGroup" value="${board.reGroup}">
            <input type="hidden" id="reLevel" name="reLevel" value="` + reLevel + `">
            <div class="mt-1">
                <label for="subject" class="form-label"></label>
                <input type="text" class="form-control" id="subject" placeholder="제목을 쓰세요" name="subject">
            </div>

            <div class="mb-1">
                <label for="content" class="form-label"></label>
                <textarea name="content" id="content" placeholder="내용을 입력하세요." rows="2" class="form-control"></textarea>
            </div>
            <div>
                <button class="btn btn-primary" id="btn-reply" type="button" onclick="btnReply()">CONFIRM</button>
                <button class="btn btn-secondary" type="reset">RESET</button>
            </div>
        </form>
    </div>
        `;
    }

    function btnReply() {
        $.ajax({
            url: "/board/reply",
            method: "POST",
            data: {
                userID: $("#userID").val(),
                userName: $("#userName").val(),
                reGroup: $("#reGroup").val(),
                reLevel: $("#reLevel").val(),
                subject: $("#subject").val(),
                content: $("#content").val(),
            },
            success: function (data) {
                console.log(data);

                if (data.success === true) {
                    location.reload();
                } else {
                    alert("Failed to save reply.")
                }
            }
        });
    }

    $("#div-board").on("click", function (e) {
        console.log(e.target);
        if ($("#div-board #form-reply").length === 0) {
            $("#rep-body div").remove();
            $(this).append(getFormText(0));
        }
    });

    $("#rep-body").on("click", "> *", function (e) {
        if (e.currentTarget.tagName !== 'TR') return;
        else if ($(this).find('#td-reStep').text() > 1) return;

        if ($(this).next('#form-reply').length === 0) {
            $("#div-board #form-reply").remove();
            $("#rep-body div").remove();
            $(this).after(getFormText($(this).find('#td-reLevel').text()));
        }
    });

</script>

</body>
</html>
