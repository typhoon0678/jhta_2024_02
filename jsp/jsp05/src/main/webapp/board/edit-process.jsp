<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/17/24
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
    String boardNo = request.getParameter("boardNo");

    String[] values = {subject, content, boardNo};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "UPDATE BOARD SET subject = ?, content = ?, regdate = sysdate WHERE boardNo = ?", values
    );

    if (jdbcConnectionPool.getExecuteUpdate() > 0) {
        ScriptWriter.alert(response, "Success", "/board/list.jsp");
    } else {
        ScriptWriter.alert(response, "Failed", "/board/list.jsp");
    }

    jdbcConnectionPool.close();
%>