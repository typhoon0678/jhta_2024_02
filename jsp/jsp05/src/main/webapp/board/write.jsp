<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <form action="${pageContext.request.contextPath}/board/write-process.jsp" method="post">
        <h1 class="h3 mb-3 fw-normal">Writes</h1>

        <div class="form-floating">
            <input type="hidden" class="form-control" name="userID" id="floatingInput" placeholder="Write ID" check-id="" value="<%=userID%>">
            <label for="floatingInput"></label>
        </div>

        <div class="form-floating">
            <input type="hidden" class="form-control" name="userName" id="floatingUsername" placeholder="Write Username" value="<%=userName%>">
            <label for="floatingUsername"></label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="subject" id="floatingSubject" placeholder="Write Subject">
            <label for="floatingSubject">Subject</label>
        </div>

        <div class="form-floating">
            <textarea class="form-control" name="content" id="floatingContent" placeholder="Write Content"></textarea>
            <label for="floatingContent">Content</label>
        </div>

        <div class="d-flex align-items-center">
            <button class="btn btn-primary w-100 py-2 my-4" id="btn-confirm" type="submit">Confirm</button>
            <button class="btn btn-secondary w-100 py-2 my-4" id="btn-reset" type="button">Reset</button>
        </div>
    </form>
</div>

<script>

</script>
</body>
</html>
