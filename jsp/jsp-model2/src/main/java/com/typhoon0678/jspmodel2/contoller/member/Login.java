package com.typhoon0678.jspmodel2.contoller.member;

import com.typhoon0678.jspmodel2.dao.MemberDao;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import com.typhoon0678.jspmodel2.dto.SessionMemberDto;
import com.typhoon0678.jspmodel2.util.CookieManager;
import com.typhoon0678.jspmodel2.util.CustomAlert;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/member/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("userID");
        String userPW = req.getParameter("userPW");
        String isRememberID = req.getParameter("isRememberID");

        HttpSession session = req.getSession();

        MemberDto memberDto = MemberDto.builder()
                .userID(userID)
                .userPW(userPW)
                .build();

        MemberDao dao = new MemberDao();

        if (dao.checkMember(memberDto)) {
            MemberDao dao2 = new MemberDao();

            SessionMemberDto sessionMemberDto = dao2.getMember(memberDto.getUserID());

            session.setAttribute("member", sessionMemberDto);

            if (isRememberID != null && isRememberID.equals("yes")) {
                CookieManager.createCookie(resp, "rememberID", sessionMemberDto.getUserID(), 60 * 60 * 24 * 30);
            } else {
                CookieManager.deleteCookie(resp, "rememberID");
            }

            CustomAlert.setAlert(req, resp, "Login Success", "/index");

        } else {
            CustomAlert.setErrorAlert(req, resp, "Invalid userID or password", "/member/login.jsp");

        }

    }
}
