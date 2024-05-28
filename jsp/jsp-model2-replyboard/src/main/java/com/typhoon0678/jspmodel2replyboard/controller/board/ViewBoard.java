package com.typhoon0678.jspmodel2replyboard.controller.board;

import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;
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
        String boardNo = req.getParameter("no");

        BoardDao dao = new BoardDao();
        dao.updateBoardHit(boardNo);

        BoardDao dao2 = new BoardDao();
        BoardDto board = dao2.getBoard(boardNo);
        req.setAttribute("board", board);

        BoardDao dao3 = new BoardDao();
        req.setAttribute("reply", dao3.getBoardByReGroup(board));

        req.getRequestDispatcher("/WEB-INF/board/view.jsp").forward(req, resp);
    }
}
