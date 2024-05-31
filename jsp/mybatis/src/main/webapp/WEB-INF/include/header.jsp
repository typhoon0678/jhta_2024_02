<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <div class="col-md-3 mb-2 mb-md-0">
        <a href="${pageContext.request.contextPath}/index"
           class="d-inline-flex link-body-emphasis text-decoration-none">
            <h2 class="ms-4">JSP Model2</h2>
        </a>
    </div>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="${pageContext.request.contextPath}/index" class="nav-link px-2">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/member/list" class="nav-link px-2">List Member</a></li>
        <li><a href="${pageContext.request.contextPath}/board/list" class="nav-link px-2">List Board</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <c:if test="${not empty member}">
            <a href="${pageContext.request.contextPath}/member/info" class="me-2" style="text-decoration: none">
                <img src="${member.renameProfile}" style="object-fit: contain; height: 50px; width: 50px;">
            </a>
            <a href="${pageContext.request.contextPath}/member/logout" class="btn btn-outline-primary me-4">Logout</a>
        </c:if>
        <c:if test="${empty member}">
            <a href="${pageContext.request.contextPath}/member/insert" class="btn btn-primary me-2">Sign-up</a>
            <a href="${pageContext.request.contextPath}/member/login" class="btn btn-outline-primary me-4">Login</a>
        </c:if>
    </div>
</header>
