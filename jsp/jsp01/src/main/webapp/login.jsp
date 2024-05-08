<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
<%
    String userID = request.getParameter("userID02");
    String userPW = request.getParameter("userPW02");

    if (userID.equals("jjang051") && userPW.equals("1234")) {
        response.sendRedirect("login_success.jsp");
    } else {
        out.println("<script>alert(\"아이디 비밀번호 확인해 주세요.\");" +
                "history.back();</script>"
        );
    }
%>
</body>
</html>
