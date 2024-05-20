<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userID = request.getParameter("userID");

    String[] values = {userID};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool("delete from member where userId = ?", values);

    if (jdbcConnectionPool.getExecuteUpdate() > 0) {
        ScriptWriter.alert(response, userID + "님 삭제되었습니다.", "/member/list-member.jsp");
    }
%>
