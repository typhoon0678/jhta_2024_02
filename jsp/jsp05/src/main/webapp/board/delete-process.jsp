<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/17/24
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String boardNo = request.getParameter("boardNo");
    String userID = CookieManager.readCookie(request, "userID");
    String password = request.getParameter("password");

    String[] values = {boardNo, userID, password};

    System.out.println(Arrays.toString(values));

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "DELETE FROM board WHERE boardNo = ? AND userid = (SELECT userid FROM MEMBER WHERE userid = ? AND userpw = ?)",
            values
    );

    int result = jdbcConnectionPool.getExecuteUpdate();
    if (result > 0) {
        ScriptWriter.alert(response, "Deleted", "/board/list.jsp");
    } else {
        ScriptWriter.alert(response, "Failed", "/board/list.jsp");
    }

    jdbcConnectionPool.close();
%>