<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CookieManager.deleteCookie(response, "userID");
    CookieManager.deleteCookie(response, "userName");

    ScriptWriter.alert(response, "Logout Successes.", "/");
%>