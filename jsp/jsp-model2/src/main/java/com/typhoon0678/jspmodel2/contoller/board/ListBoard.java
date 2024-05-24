package com.typhoon0678.jspmodel2.contoller.board;

import com.typhoon0678.jspmodel2.dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/board/list")
public class ListBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardDao dao = new BoardDao();

        req.setAttribute("boardList", dao.getAllBoard());

        req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
    }
}
