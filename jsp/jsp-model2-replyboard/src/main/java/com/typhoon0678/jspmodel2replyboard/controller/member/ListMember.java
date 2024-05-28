package com.typhoon0678.jspmodel2replyboard.controller.member;

import com.typhoon0678.jspmodel2replyboard.dao.MemberDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/member/list")
public class ListMember extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDao dao = new MemberDao();

        req.setAttribute("memberList", dao.getMembers());

        req.getRequestDispatcher("/WEB-INF/member/list-member.jsp").forward(req, resp);
    }
}
