package com.typhoon0678.mybatis.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.typhoon0678.mybatis.dao.MemberDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member/delete-admin")
public class DeleteMemberAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String userID = req.getParameter("userID");

        MemberDao dao = new MemberDao();

        int result = dao.deleteMemberAdmin(userID);
        dao.close();

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("isDeleted", (result > 0));

        Gson gson = new Gson();
        out.println(gson.toJson(resultMap));

        out.flush();
    }
}
