<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.typhoon0678.jsp05.connect.JdbcConnectionPool" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/17/24
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
    String boardNo = request.getParameter("boardNo");
    String userID = CookieManager.readCookie(request, "userID");

    String[] values = {boardNo, userID};

    System.out.println(Arrays.toString(values));

    JdbcConnectionPool jdbcConnectionPool = new JdbcConnectionPool(
            "DELETE FROM board WHERE boardNo = ? AND userid = ?",
            values
    );

    int result = jdbcConnectionPool.getExecuteUpdate();


%>
{
    "isDelete":
    <%=(result > 0) ? "\"yes\"" : "\"no\""%>
}

<%--<%--%>
<%--    response.setContentType("application/json; charset=utf-8");--%>
<%--    Map<String, String> resultMap = new HashMap<>();--%>

<%--    if (result > 0) {--%>
<%--        resultMap.put("isDelete", "yes");--%>
<%--    } else {--%>
<%--        resultMap.put("isDelete", "no");--%>
<%--    }--%>

<%--    Gson gson = new Gson();--%>
<%--    String returnJson = gson.toJson(resultMap);--%>
<%--    out.println(returnJson);--%>
<%--%>--%>