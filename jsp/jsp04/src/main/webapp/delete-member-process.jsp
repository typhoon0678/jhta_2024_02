<%@ page import="com.typhoon0678.jsp04.util.CookieManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Class.forName("oracle.jdbc.OracleDriver");
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    String userID = CookieManager.readCookie(request, "userID");
    String userPW = request.getParameter("userPW");

    String sql = "DELETE FROM MEMBER WHERE userID = ? AND userPW = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, userID);
    preparedStatement.setString(2, userPW);

    int result = preparedStatement.executeUpdate();

    if (result > 0) {
        CookieManager.deleteCookie(response, "userID");
        CookieManager.deleteCookie(response, "userName");
        CookieManager.deleteCookie(response, "rememberID");

        out.print("<script>");
        out.print("alert(\"Delete " + userID + " Successes.\");");
        out.print("location.href='/'");
        out.print("</script>");
    } else {
        out.print("<script>");
        out.print("alert(\"Delete " + userID + " Failed.\");");
        out.print("location.href='/'");
        out.print("</script>");
    }
%>