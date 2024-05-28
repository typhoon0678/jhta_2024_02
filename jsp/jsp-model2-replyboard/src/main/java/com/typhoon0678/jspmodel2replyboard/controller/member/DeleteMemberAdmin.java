package com.typhoon0678.jspmodel2replyboard.controller.member;

import com.google.gson.Gson;
import com.typhoon0678.jspmodel2replyboard.dao.MemberDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/member/delete-admin")
public class DeleteMemberAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String userID = req.getParameter("userID");
        System.out.println(userID);

        MemberDao dao = new MemberDao();

        boolean result = dao.deleteMemberAdmin(userID);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("isDeleted", result);

        Gson gson = new Gson();
        out.println(gson.toJson(resultMap));

        out.flush();
    }
}
