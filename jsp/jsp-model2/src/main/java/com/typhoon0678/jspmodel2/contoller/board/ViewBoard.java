package com.typhoon0678.jspmodel2.contoller.board;

import com.typhoon0678.jspmodel2.dao.BoardDao;
import com.typhoon0678.jspmodel2.dto.BoardDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/board/view")
public class ViewBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardNo = req.getParameter("boardno");

        BoardDao dao = new BoardDao();
        dao.updateBoardHit(boardNo);

        BoardDao dao2 = new BoardDao();
        BoardDto board = dao2.getBoard(boardNo);
        req.setAttribute("board", board);

        req.getRequestDispatcher("/WEB-INF/board/view.jsp").forward(req, resp);
    }
}
