<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/20/24
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL Test</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <h2>JSTL</h2>

    <c:set var="pageVar" value="pageVariable"/>
    <c:set var="requestVar" value="requestVariable" scope="request"/>
    <c:set var="sessionVar" value="sessionVariable" scope="session"/>
    <c:set var="applicationVar" value="applicationVariable" scope="application"/>
    <c:set var="betweenVar">betweenVariable</c:set>

    <c:set var="number">11</c:set>
    <c:set var="string">JAVA</c:set>

    <ul>
        <li>Set var at page : ${pageScope.pageVar}</li>
        <li>Set var at request : ${requestScope.requestVar}</li>
        <li>Set var at session : ${sessionScope.sessionVar}</li>
        <li>Set var at application : ${applicationScope.applicationVar}</li>
        <li>Set betweenVar : ${betweenVar}</li>
        <hr>

        <c:if test="${number % 2 == 0}">
            <li>${number} 는 짝수</li>
        </c:if>

        <c:if test="${number % 2 != 0}">
            <li>${number} 는 홀수</li>
        </c:if>

        <c:if test="${string eq 'JAVA'}">
            <li>${string} == JAVA</li>
        </c:if>

        <c:if test="true">
            <li>test == true</li>
        </c:if>
        <c:if test="TRUE">
            <li>test == TRUE</li>
        </c:if>
        <c:if test="${true}">
            <li>test == {true}</li>
        </c:if>

        <c:choose>
            <c:when test="${number % 2 == 0}">
                <li>${number} is even</li>
            </c:when>
            <c:otherwise>
                <li>${number} is odd</li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${number mod 2 eq 0}">
                <li>${number} is even</li>
            </c:when>
            <c:otherwise>
                <li>${number} is odd</li>
            </c:otherwise>
        </c:choose>
    </ul>

    <ul>
        <li>
            <c:forEach begin="1" end="20" step="1" var="i">
                ${i}
            </c:forEach>
        </li>
    </ul>

    <c:set var="total" value="66"/>

    <c:choose>
        <c:when test="${total != 0}">

            <table class="table table-striped">
                <th>ID</th>
                <th>Subject</th>
                <th>Author</th>

                <c:forEach begin="1" end="10" step="1" var="idx" varStatus="loop">
                    <tr>
                        <td>${total - idx}</td>
                        <td>subject${idx}</td>
                        <td>user${idx}</td>
                    </tr>
                </c:forEach>
            </table>
            <c:forEach begin="1" end="${total / 10 + 1}" step="1" var="page" varStatus="loop">
                <a href="jstl-test.jsp?page=${page}">${page}</a>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <h2>No Content</h2>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
