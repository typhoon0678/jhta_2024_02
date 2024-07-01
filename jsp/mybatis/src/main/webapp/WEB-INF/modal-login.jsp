<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<div class="modal fade" id="modal-login" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">${modal.title}</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${modal.msg}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="location.href='${modal.location}'">OK</button>
            </div>
        </div>
    </div>
</div>

<script>
    const modal = new bootstrap.Modal("#modal-login");
    modal.show();
</script>

<c:remove var="modal" scope="session"/>

</body>
