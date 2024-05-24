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

@WebServlet("/member/delete")
public class DeleteMember extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/member/delete-member.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        SessionMemberDto dto = (SessionMemberDto) session.getAttribute("member");
        String userPW = req.getParameter("userPW");

        MemberDao dao = new MemberDao();
        MemberDao dao2 = new MemberDao();

        MemberDto memberDto = MemberDto.builder()
                .userID(dto.getUserID())
                .userPW(userPW)
                .build();

        if (dao.checkMember(memberDto) && dao2.deleteMember(memberDto)) {
            session.invalidate();
            CookieManager.deleteCookie(resp, "rememberID");

            CustomAlert.setAlert(req, resp, "Member Deleted", "/index");

        } else {
            CustomAlert.setAlert(req, resp, "Please Check your Password", "/member/delete-member");

            req.getRequestDispatcher("/WEB-INF/member/delete-member.jsp").forward(req, resp);
        }
    }
}
