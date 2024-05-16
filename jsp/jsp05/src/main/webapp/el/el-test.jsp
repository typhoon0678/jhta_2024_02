<%@ page import="com.typhoon0678.jsp05.dto.MemberDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/14/24
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el Test</title>
</head>
<body>
<%
    pageContext.setAttribute("scope", "pageContext");
    request.setAttribute("scope", "request");
    session.setAttribute("scope", "session");
    application.setAttribute("scope", "application");

    request.setAttribute("memberDto", new MemberDto(30, "name", "userID1", "1234"));

    pageContext.setAttribute("num01", 1);
    pageContext.setAttribute("num02", 2);

    List<MemberDto> memberDtoList = new ArrayList<>();
    memberDtoList.add(new MemberDto(30, "name1", "userID1", "1234"));
    memberDtoList.add(new MemberDto(30, "name2", "userID2", "1234"));
    request.setAttribute("memberDtoList", memberDtoList);

    Map<String, MemberDto> memberDtoMap = new HashMap<>();
    memberDtoMap.put("person1", new MemberDto(30, "name1", "userID1", "1234"));
    memberDtoMap.put("person2", new MemberDto(30, "name2", "userID2", "1234"));
    request.setAttribute("memberDtoMap", memberDtoMap);

    request.setAttribute("objNull", null);
%>
<p>${pageScope.scope}</p>
<p>${requestScope.scope}</p>
<p>${sessionScope.scope}</p>
<p>${applicationScope.scope}</p>

<hr>

<h2>Get param</h2>
<p>${param.name}</p>
<p>${param.userID}</p>
<p>${paramValues.inter[0]}</p>
<p>${paramValues.inter[1]}</p>

<hr>

<h2>Get initParam</h2>
<p>${initParam.OracleDriver}</p>
<p>${initParam.OracleUrl}</p>

<hr>

<h2>Get cookie</h2>
<p>${cookie.userID.value}</p>
<p>${cookie.userName.value}</p>

<hr>

<h2>Get Object</h2>
<p>${requestScope.memberDto.age}</p>
<p>${requestScope.memberDto.name}</p>
<p>${requestScope.memberDto.userID}</p>
<p>${requestScope.memberDto.userPW}</p>
<hr>

<h2>Get context</h2>
<p><a href="${pageContext.request.contextPath}/index.jsp">Index</a></p>
<p>${num01 + num02}</p>
<p>${num01 < num02}</p>
<br>
<p>${num01 gt num02}</p>
<p>${num01 lt num02}</p>
<p>${num01 ge num02}</p>
<p>${num01 le num02}</p>
<p>${num01 eq num02}</p>
<p>${num01 ne num02}</p>
</body>

<hr>

<h2>Get List, Map</h2>
<p><%
    for (MemberDto member : memberDtoList) {
        out.print(member.getAge() + ", ");
        out.print(member.getName() + ", ");
        out.print(member.getUserID() + " ");
    }
%></p>
<br>
<p>${memberDtoMap.person1.name}</p>
<p>${memberDtoMap.person2.name}</p>

<hr>

<h2>Ternary operator</h2>
<p>${(num01 > num02) ? "num01 > num02" : "num01 < num02"}</p>
<p>${objNull == null}</p>
</html>
