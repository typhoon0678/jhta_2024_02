package com.typhoon0678.jspmodel2replyboard.controller.board;

import java.io.IOException;
import java.util.List;

import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/search")
public class SearchBoard extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String search = req.getParameter("search");
		String searchWord = req.getParameter("searchWord");

		BoardDao dao = new BoardDao();
		List<BoardDto> searchBoard = dao.searchBoard(search, searchWord);

		req.setAttribute("boardList", searchBoard);

		req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
	}
}
