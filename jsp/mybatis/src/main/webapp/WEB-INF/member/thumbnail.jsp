<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/31/24
  Time: 4:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Thumbnail</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <form action="${pageContext.request.contextPath}/member/thumbnail" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="profile" class="form-label">PROFILE</label>
            <input class="form-control" type="file" id="profile" name="profile"
                   accept="image/gif, image/jpg, image/png">
            <div class="m-2" id="preview"></div>
        </div>

        <button class="btn btn-primary" type="submit">Submit</button>
    </form>
</div>

<script>
    $("#profile").on("change", function (e) {

        const file = e.currentTarget.files[0];

        if (!file) {
            $("#preview").html("");
            return false;
        }

        const extension = file.name.substring(file.name.lastIndexOf(".") + 1).toLowerCase();

        if (["png", "jpg", "gif"].includes(extension)) {
            const profileReader = new FileReader();
            profileReader.addEventListener("load", function (event) {
                console.log(event);
                const img = event.currentTarget.result;
                console.log(img);
                $("#preview").html(`<img src=\${img}>`);
            });
            profileReader.readAsDataURL(file);

        } else {
            alert("Please Upload Image");
            $(this).val("");
            return false;
        }
    });
</script>

<c:if test="${not empty requestScope.error}">
    <script>
        window.addEventListener("load", function () {
            alert("${error}");
        });
    </script>
</c:if>

</body>
</html>
