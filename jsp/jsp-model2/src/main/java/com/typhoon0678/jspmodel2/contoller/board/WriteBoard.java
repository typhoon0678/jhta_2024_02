package com.typhoon0678.jspmodel2.contoller.board;

import com.typhoon0678.jspmodel2.dao.BoardDao;
import com.typhoon0678.jspmodel2.dto.BoardDto;
import com.typhoon0678.jspmodel2.util.CustomAlert;
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

        BoardDto boardDto = BoardDto.builder()
                .subject(req.getParameter("subject"))
                .content(req.getParameter("content"))
                .userID(req.getParameter("userID"))
                .userName(req.getParameter("userName"))
                .hit(0)
                .build();

        BoardDao dao = new BoardDao();
        boolean result = dao.addBoard(boardDto);

        if (result) {
            CustomAlert.setAlert(req, resp, "Board Created", "/board/list");

        } else {
            CustomAlert.setErrorAlert(req, resp, "Password Incorrect", "/board/write.jsp");

        }

    }
}
