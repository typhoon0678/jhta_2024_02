<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String subject = request.getParameter("content");
    String content = request.getParameter("content");
    String userID = request.getParameter("userID");
    String userName = request.getParameter("userName");

    String[] values = {subject, content, userID, userName};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "INSERT INTO BOARD (boardNo, subject, content, userID, userName) VALUES(board_seq.nextval, ?, ?, ?, ?)", values);

    if (jdbcConnectionPool.getExecuteUpdate() > 0) {
        ScriptWriter.alert(response, "Success", "/board/list.jsp");
    } else {
        ScriptWriter.alert(response, "Failed", "/board/list.jsp");
    }
%>