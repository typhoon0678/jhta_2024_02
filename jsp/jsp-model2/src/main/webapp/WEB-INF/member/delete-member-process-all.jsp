<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.typhoon0678.jsp05.util.ScriptWriter" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/16/24
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool("DELETE FROM MEMBER WHERE userNo = ?");

    try {
        jdbcConnectionPool.connection.setAutoCommit(false);

        String[] values = request.getParameterValues("check");
        for (String value : values) {
            jdbcConnectionPool.getPreparedStatement().setInt(1, Integer.parseInt(value));
            jdbcConnectionPool.getPreparedStatement().addBatch();
        }

        int[] result = jdbcConnectionPool.getPreparedStatement().executeBatch();
        System.out.println(Arrays.toString(result));

        jdbcConnectionPool.connection.commit();

        ScriptWriter.alert(response, "삭제 완료되었습니다.", "/member/list-member.jsp");

    } catch (Exception e) {
        jdbcConnectionPool.connection.rollback();
        e.printStackTrace();

        ScriptWriter.alert(response, "삭제에 실패했습니다.", "/member/list-member.jsp");

    } finally {
        jdbcConnectionPool.connection.setAutoCommit(true);

        jdbcConnectionPool.close();
    }
%>