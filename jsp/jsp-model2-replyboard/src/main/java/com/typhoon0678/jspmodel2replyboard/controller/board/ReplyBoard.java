package com.typhoon0678.jspmodel2replyboard.controller.board;

import com.google.gson.Gson;
import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dao.MemberDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/board/reply")
public class ReplyBoard extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        int reGroup = Integer.parseInt(req.getParameter("reGroup"));
        int reLevel = Integer.parseInt(req.getParameter("reLevel"));
        int reStep = 1;

        BoardDao dao = new BoardDao();

        if (reLevel <= 0) {
            reLevel = dao.getMaxReLevel(reGroup) + 1;
        } else {
            reStep = dao.getMaxReStep(reGroup, reLevel) + 1;
        }

        System.out.println("reGroup: " + reGroup);
        System.out.println("reLevel: " + reLevel);
        System.out.println("reStep: " + reStep);

        BoardDto boardDto = BoardDto.builder()
                .subject(req.getParameter("subject"))
                .content(req.getParameter("content"))
                .userID(req.getParameter("userID"))
                .userName(req.getParameter("userName"))
                .reGroup(reGroup)
                .reLevel(reLevel)
                .reStep(reStep)
                .build();

        BoardDao dao2 = new BoardDao();

        Map<String, Boolean> checkMap = new HashMap<>();
        checkMap.put("success", dao2.addBoard(boardDto));

        Gson gson = new Gson();
        out.println(gson.toJson(checkMap));

        out.flush();
    }
}
