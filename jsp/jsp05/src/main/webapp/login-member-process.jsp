<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");

    String[] values = {userID, userPW};

    ServletContext context = request.getServletContext();

    JdbcConnect jdbcConnect = new JdbcConnect(
            context, "SELECT userid, username from member WHERE userid = ? AND userpw = ?", values);

    String isRememberID = request.getParameter("isRememberID");

    jdbcConnect.setResultSet();
    ResultSet resultSet = jdbcConnect.getResultSet();

    if (resultSet.next()) {
        String savedUserID = resultSet.getString("userID");
        String savedUserName = resultSet.getString("userName");

        CookieManager.createCookie(response, "userID", savedUserID, 60 * 60 * 24);
        CookieManager.createCookie(response, "userName", savedUserName, 60 * 60 * 24);
        if (isRememberID != null && isRememberID.equals("yes")) {
            CookieManager.createCookie(response, "rememberID", savedUserID, 60 * 60 * 24 * 30);
        } else {
            CookieManager.deleteCookie(response, "rememberID");
        }

        ScriptWriter.alert(response, "Login Successes.", "/");
    } else {
        ScriptWriter.alert(response, "Login Failed.", "login.jsp");
    }

    jdbcConnect.close();
%>