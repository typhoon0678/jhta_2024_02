<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.typhoon0678.jspmodel2.mail.NaverMail" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/22/24
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String> sendMailInfo = new HashMap<>();

    sendMailInfo.put("from", "test@naver.com");
    sendMailInfo.put("to", "Test@naver.com");
    sendMailInfo.put("subject", "Reset Password");
    sendMailInfo.put("content", "1234");
    sendMailInfo.put("format", "text/plain;charset=UTF-8");

    try {
        NaverMail naverMail = new NaverMail(application);
        naverMail.sendMail(sendMailInfo);
        System.out.println("success");
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("fail");
    }
%>