<%@ page import="java.time.LocalDate" %><%--
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/8/24
  Time: 4:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LocalDate now = LocalDate.now();
    int year = now.getYear();
    int age = Integer.parseInt( request.getParameter("age"));
    if(year - age>=18) {
        response.sendRedirect("adult_ok.jsp");
    } else {
        out.println("<script>alert(\"성인만 입장 가능합니다.\");history.back();</script>");
    }
%>s