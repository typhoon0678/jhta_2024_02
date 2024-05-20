<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnect" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = request.getParameter("userID");
    String userPW = request.getParameter("userPW");
    String userName = request.getParameter("userName");
    String userEmail = request.getParameter("userEmail");
    String userPostCode = request.getParameter("userPostCode");
    String userAddress = request.getParameter("userAddress");
    String userDetailAddress = request.getParameter("userDetailAddress");
    String userBirth = request.getParameter("userBirth");

    String[] values = {userID, userPW, userName, userEmail, userPostCode, userAddress, userDetailAddress, userBirth};

    ServletContext context = request.getServletContext();

    JdbcConnect jdbcConnect = new JdbcConnect(
            context, "INSERT INTO member VALUES (member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)", values);


    int result = jdbcConnect.getPreparedStatement().executeUpdate();

    if (result > 0) {
        ScriptWriter.alert(response, "Sign Up Successes.", "/");
    } else {
        ScriptWriter.alert(response, "Sign Up Failed.", "/member/insert-member.jsp");
    }

    jdbcConnect.close();
%>