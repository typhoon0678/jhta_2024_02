<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="oracle.sql.json.OracleJsonObject" %>
<%@ page import="oracle.jdbc.driver.json.tree.OracleJsonObjectImpl" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String[] values = {request.getParameter("userID")};

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "SELECT COUNT(*) AS count FROM MEMBER WHERE userid = ?", values);

    jdbcConnectionPool.setResultSet();

    ResultSet resultSet = jdbcConnectionPool.getResultSet();

    Gson gson = new Gson();

    if (resultSet.next()) {
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        countMap.put("count", resultSet.getInt("count"));


        response.setContentType("application/json;charset=UTF-8");
        out.print(gson.toJson(countMap));
//        out.print("{\"count\": " + count + "}");
    }

    jdbcConnectionPool.close();
%>
