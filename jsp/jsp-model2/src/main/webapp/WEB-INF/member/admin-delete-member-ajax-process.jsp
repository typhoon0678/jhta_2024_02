<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // history.back()을 하면 refresh가 같이 발생을 함,,,, 크롬도 막힌거 같음...
    String userID = request.getParameter("userID");
    String[] values = {userID};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool("delete from member where userId = ?", values);

    int result = jdbcConnectionPool.getExecuteUpdate();
    response.setContentType("application/json; charset=utf-8");
    Map<String, String> resultMap = new HashMap<>();
    if (result > 0) {
        resultMap.put("isDelete", "yes");
    } else {
        resultMap.put("isDelete", "no");
    }
    Gson gson = new Gson();
    String returnJson = gson.toJson(resultMap); //json으로 변환해줌...
    out.println(returnJson);
%>