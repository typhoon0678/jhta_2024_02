<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = CookieManager.readCookie(request, "userID");
    String userPW = request.getParameter("userPW");

    String[] values = {userID, userPW};

    ServletContext context = request.getServletContext();

    JdbcConnect jdbcConnect = new JdbcConnect(
            context, "DELETE FROM MEMBER WHERE userID = ? AND userPW = ?", values);


    int result = jdbcConnect.getPreparedStatement().executeUpdate();

    if (result > 0) {
        CookieManager.deleteCookie(response, "userID");
        CookieManager.deleteCookie(response, "userName");
        CookieManager.deleteCookie(response, "rememberID");

        ScriptWriter.alert(response, "Delete " + userID + " Successes.", "/");
    } else {
        ScriptWriter.alert(response, "Delete " + userID + " Failed.", "/");
    }

    jdbcConnect.close();
%>