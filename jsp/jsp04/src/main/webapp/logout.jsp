<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CookieManager.deleteCookie(response, "userID");
    CookieManager.deleteCookie(response, "userName");

    out.print("<script>");
    out.print("alert(\"Logout Successes\");");
    out.print("location.href='/'");
    out.print("</script>");
%>
