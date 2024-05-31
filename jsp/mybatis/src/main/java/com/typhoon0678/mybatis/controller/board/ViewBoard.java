package com.typhoon0678.mybatis.controller.board;

import java.io.IOException;

import com.typhoon0678.mybatis.dao.BoardDao;
import com.typhoon0678.mybatis.dto.BoardDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/view")
public class ViewBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));

		BoardDao dao = new BoardDao();

		BoardDto boardDto = dao.getBoard(boardNo);
		dao.close();

		req.setAttribute("board", boardDto);

		req.getRequestDispatcher("/WEB-INF/board/view.jsp").forward(req, resp);
	}
}
