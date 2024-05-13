<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Class.forName("oracle.jdbc.OracleDriver");
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");
    String isRememberID = request.getParameter("isRememberID");

    String sql = "SELECT userid, username from member WHERE userid = ? AND userpw = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, userID);
    preparedStatement.setString(2, userPW);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
        String savedUserID = resultSet.getString("userID");
        String savedUserName = resultSet.getString("userName");

        CookieManager.createCookie(response, "userID", savedUserID, 60*60*24);
        CookieManager.createCookie(response, "userName", savedUserName, 60*60*24);
        if (isRememberID != null && isRememberID.equals("yes")) {
            CookieManager.createCookie(response, "rememberID", savedUserID, 60*60*24*30);
        } else {
            CookieManager.deleteCookie(response, "rememberID");
        }

        out.print("<script>");
        out.print("alert(\"Login Successes\");");
        out.print("location.href='/'");
        out.print("</script>");
    } else {
        out.print("<script>");
        out.print("alert(\"Login Failed\");");
        out.print("location.href='login.jsp'");
        out.print("</script>");
    }
%>