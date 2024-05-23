package com.typhoon0678.jspmodel2.contoller.member;

import com.typhoon0678.jspmodel2.dao.MemberDao;
import com.typhoon0678.jspmodel2.dto.MemberDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/member/insert")
public class InsertMember extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/member/insert-member.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String userPW = BCrypt.hashpw(req.getParameter("userPW"), BCrypt.gensalt());

        MemberDto memberDto = MemberDto.builder()
                .userID(req.getParameter("userID"))
                .userName(req.getParameter("userName"))
                .userPW(userPW)
                .email(req.getParameter("userEmail"))
                .postcode(req.getParameter("userPostCode"))
                .address(req.getParameter("userAddress"))
                .detailAddress(req.getParameter("userDetailAddress"))
                .grade("member")
                .birth(req.getParameter("userBirth"))
                .build();

        MemberDao dao = new MemberDao();

        int result = dao.insertMember(memberDto);
        if (result > 0) {
            req.getRequestDispatcher("/WEB-INF/member/insert-member-process.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Error inserting member");

            req.getRequestDispatcher("/WEB-INF/member/insert-member.jsp").forward(req, resp);
        }

    }
}
