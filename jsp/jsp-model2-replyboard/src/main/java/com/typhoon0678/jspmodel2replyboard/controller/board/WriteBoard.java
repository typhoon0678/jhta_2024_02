package com.typhoon0678.jspmodel2replyboard.controller.board;

import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;
import com.typhoon0678.jspmodel2replyboard.util.CustomAlert;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/board/write")
public class WriteBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/board/write.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardDao dao = new BoardDao();

        int reGroup = dao.getMaxReGroup() + 1;

        BoardDto boardDto = BoardDto.builder()
                .subject(req.getParameter("subject"))
                .content(req.getParameter("content"))
                .userID(req.getParameter("userID"))
                .userName(req.getParameter("userName"))
                .reGroup(reGroup)
                .reLevel(1)
                .reStep(1)
                .build();

        BoardDao dao2 = new BoardDao();
        boolean result = dao2.addBoard(boardDto);

        if (result) {
            CustomAlert.setAlert(req, resp, "Board Created", "/board/list");

        } else {
            CustomAlert.setErrorAlert(req, resp, "Something Wrong", "/board/write.jsp");

        }

    }
}
