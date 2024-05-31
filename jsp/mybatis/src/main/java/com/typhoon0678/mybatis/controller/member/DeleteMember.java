package com.typhoon0678.mybatis.controller.member;

import java.io.IOException;

import com.typhoon0678.mybatis.dao.MemberDao;
import com.typhoon0678.mybatis.dto.MemberDto;
import com.typhoon0678.mybatis.dto.SessionMemberDto;
import com.typhoon0678.mybatis.util.CookieManager;
import com.typhoon0678.mybatis.util.CustomAlert;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        MemberDto memberDto = MemberDto.builder()
                .userID(dto.getUserID())
                .userPW(userPW)
                .build();

        if (dao.checkMember(memberDto) && (dao.deleteMember(memberDto) > 0)) {
            session.invalidate();
            CookieManager.deleteCookie(resp, "rememberID");

            CustomAlert.setAlert(req, resp, "Member Deleted", "/index");

            dao.close();

        } else {
            CustomAlert.setAlert(req, resp, "Please Check your Password", "/member/delete-member");

            req.getRequestDispatcher("/WEB-INF/member/delete-member.jsp").forward(req, resp);

            dao.close();
        }
    }
}
