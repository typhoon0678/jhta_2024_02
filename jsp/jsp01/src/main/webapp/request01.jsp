<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request01</title>
</head>
<body>
<h1>request</h1>
<a href="request_main.jsp?name=hi&age=30">request_main.jsp</a>

<form method="post" action="request_main.jsp">
    <input type="text" name="name" id="name"> <br>
    <input type="number" name="age" id="age"> <br>
    <button type="submit">전송</button>
</form>

<br>

<form method="post" action="request_main02.jsp">
    <input type="text" name="userID"> <br>
    <input type="password" name="userPW"> <br>
    <label>
        <input type="radio" name="gender" value="male"><span>남자</span>
    </label>
    <label>
        <input type="radio" name="gender" value="female"><span>여자</span>
    </label>
    <select name="month">
        <option value="1">01</option>
        <option value="2">02</option>
        <option value="3">03</option>
        <option value="4">04</option>
        <option value="5">05</option>
        <option value="6">06</option>
        <option value="7">07</option>
        <option value="8">08</option>
        <option value="9">09</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
    </select> <br>
    <label>
        <input type="checkbox" name="hobby" value="movie"><span>Watch Movie</span>
        <input type="checkbox" name="hobby" value="fishing"><span>Fishing</span>
        <input type="checkbox" name="hobby" value="go"><span>Go</span>
    </label> <br>
    <textarea name="content"></textarea>
    <button type="submit">전송</button>
</form>

<form action="login.jsp" method="post">
    <input type="text" placeholder="user id" name="userID02"> <br>
    <input type="password" placeholder="user pw" name="userPW02"> <br>
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
