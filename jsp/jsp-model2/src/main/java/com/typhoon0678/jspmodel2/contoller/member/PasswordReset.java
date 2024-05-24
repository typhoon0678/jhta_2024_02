package com.typhoon0678.jspmodel2.contoller.member;

import com.typhoon0678.jspmodel2.dao.MemberDao;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import com.typhoon0678.jspmodel2.util.CustomAlert;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/member/password-reset")
public class PasswordReset extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/member/password-reset.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("userID");
        String userPW = req.getParameter("userPW");

        String hashPW = BCrypt.hashpw(userPW, BCrypt.gensalt());

        MemberDto memberDto = MemberDto.builder()
                .userID(userID)
                .userPW(hashPW)
                .build();

        MemberDao dao = new MemberDao();

        boolean result = dao.resetPassword(memberDto);

        if (result) {
            CustomAlert.setAlert(req, resp, "Password Changed", "/index");

        } else {
            CustomAlert.setErrorAlert(req, resp, "Error Reset Password", "/password-reset.jsp");

        }
    }
}
