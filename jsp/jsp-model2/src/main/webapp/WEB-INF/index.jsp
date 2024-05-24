<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@include file="include/header.jsp" %>

<c:if test="${not empty member}">
    <h1>Hi, ${member.userName}</h1>
</c:if>
<c:if test="${empty member}">
    <h1>Hello World!</h1>
</c:if>
<br/>

<a href="hello-servlet">Hello Servlet</a>
<a href="TestForm.do">TestForm.do</a>

</body>
</html>