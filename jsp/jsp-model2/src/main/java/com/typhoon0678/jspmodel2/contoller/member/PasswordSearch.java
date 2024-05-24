package com.typhoon0678.jspmodel2.contoller.member;

import com.typhoon0678.jspmodel2.dao.MemberDao;
import com.typhoon0678.jspmodel2.mail.NaverMail;
import com.typhoon0678.jspmodel2.util.CustomAlert;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/member/password-search")
public class PasswordSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/member/password-search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext application = req.getServletContext();

        MemberDao dao = new MemberDao();

        String userID = req.getParameter("userID");
        String toEmail = dao.getMember(userID).getEmail();

        System.out.println(toEmail);
        if (toEmail == null) {
            CustomAlert.setAlert(req, resp, "Check your Email", "/index");
            return;
        }

        Map<String, String> sendMailInfo = new HashMap<>();

        sendMailInfo.put("from", application.getInitParameter("NaverID") + "@naver.com");
        sendMailInfo.put("to", toEmail);
        sendMailInfo.put("subject", "jsp-model2 - Reset Password");
        sendMailInfo.put("content", "<h2><a href=\"http://localhost:8080/member/password-reset?id=" + userID + "\">Reset Password</a></h2>");
        sendMailInfo.put("format", "text/html; charset=UTF-8");

        try {
            NaverMail naverMail = new NaverMail(application);
            naverMail.sendMail(sendMailInfo);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail");
        }

        CustomAlert.setAlert(req, resp, "Check Your Email", "/index");
    }
}
