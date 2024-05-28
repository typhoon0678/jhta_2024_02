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

@WebServlet("/member/check-id")
public class checkID extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String userID = req.getParameter("userID");

        MemberDao dao = new MemberDao();

        Map<String, Boolean> checkMap = new HashMap<>();
        checkMap.put("isDuplicated", dao.isIdDuplicated(userID));

        Gson gson = new Gson();
        out.println(gson.toJson(checkMap));

        out.flush();
    }
}
