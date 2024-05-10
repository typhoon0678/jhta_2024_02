<%@ page import="com.typhoon0678.jsp03.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");
    String saveID = request.getParameter("saveID");

    if (userID != null && userPW != null && userID.equals("user") && userPW.equals("1234")) {

        session.setAttribute("sessionUserID", userID);

        if (saveID != null && saveID.equals("yes")) {
            CookieManager.createCookie(response, "userID", userID, 60*60*24*15);
        } else {
            CookieManager.deleteCookie(response, "userID");
        }

        response.sendRedirect("/");
    } else {
        response.sendRedirect("login.jsp");
    }

%>
