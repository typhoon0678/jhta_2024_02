package com.typhoon0678.mybatis.controller.member;

import java.io.IOException;

import com.typhoon0678.mybatis.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class ListMember extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDao dao = new MemberDao();

        req.setAttribute("memberList", dao.getMembers());
        dao.close();

        req.getRequestDispatcher("/WEB-INF/member/list-member.jsp").forward(req, resp);
    }
}
