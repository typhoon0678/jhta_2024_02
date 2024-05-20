package com.typhoon0678.jspmodel2.contoller.member;

import com.typhoon0678.jspmodel2.dao.MemberDao;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/member/insert-process")
public class InsertMemberProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        MemberDto memberDto = new MemberDto();

        memberDto.setUserID(req.getParameter("userID"));
        memberDto.setUserName(req.getParameter("userName"));
        memberDto.setUserPW(req.getParameter("userPW"));
        memberDto.setEmail(req.getParameter("userEmail"));
        memberDto.setPostcode(req.getParameter("userPostCode"));
        memberDto.setAddress(req.getParameter("userAddress"));
        memberDto.setDetailAddress(req.getParameter("userDetailAddress"));
        memberDto.setBirth(req.getParameter("userBirth"));

        MemberDao memberDao = new MemberDao();

        int result = memberDao.insertMember(memberDto);
        if (result > 0) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
