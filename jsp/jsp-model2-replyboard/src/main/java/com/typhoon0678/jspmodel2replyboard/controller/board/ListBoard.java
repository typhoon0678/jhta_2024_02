package com.typhoon0678.jspmodel2replyboard.controller.board;

import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/board/list")
public class ListBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int perPage = 10;
		int total;
		int boardCount;
		List<BoardDto> pageBoardList;

		String pageStr = req.getParameter("page");
		int page = (pageStr == null || pageStr.isBlank()) ? 1 : Integer.parseInt(pageStr);

		String search = req.getParameter("search");
		String searchWord = req.getParameter("searchWord");

		if (search == null) {
			BoardDao dao = new BoardDao();
			pageBoardList = dao.getPageBoard(perPage * (page - 1) + 1, perPage * page);

			BoardDao dao2 = new BoardDao();
			boardCount = dao2.getBoardCount();
			total = (int)Math.ceil(boardCount / (double)perPage);

		} else {
			BoardDao dao = new BoardDao();
			pageBoardList = dao.searchBoard(search, searchWord, perPage * (page - 1) + 1, perPage * page);

			BoardDao dao2 = new BoardDao();
			boardCount = dao2.getSearchBoardCount(search, searchWord);
			total = (int)Math.ceil(boardCount / (double)perPage);

			req.setAttribute("search", search);
			req.setAttribute("searchWord", searchWord);
		}

		req.setAttribute("boardList", pageBoardList);
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("perPage", perPage);
		req.setAttribute("boardCount", boardCount);
		req.setAttribute("navNum", (int)Math.floor((page - 1) / (double)perPage));

		req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
	}
}
