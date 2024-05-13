<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/10/24
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Class.forName("oracle.jdbc.OracleDriver");
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jhta", "1234");

    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");
    String userName = request.getParameter("userName");
    String userBirth = request.getParameter("userBirth");

    String sql = "INSERT INTO member VALUES (member_seq.NEXTVAL, ?, ?, ?, ?)";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, userID);
    preparedStatement.setString(2, userPW);
    preparedStatement.setString(3, userName);
    preparedStatement.setString(4, userBirth);

    int result = preparedStatement.executeUpdate();

    if (result > 0) {
        out.print("<script>");
        out.print("alert(\"Sign Up Successes.\");");
        out.print("location.href='/'");
        out.print("</script>");
    } else {
        out.print("<script>");
        out.print("alert(\"Sign Up Failed.\");");
        out.print("location.href='insert-member.jsp'");
        out.print("</script>");
    }
%>