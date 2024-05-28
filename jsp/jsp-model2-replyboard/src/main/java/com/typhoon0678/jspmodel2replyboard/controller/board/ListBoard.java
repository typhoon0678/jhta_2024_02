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

		String pageStr = req.getParameter("page");
		int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

		BoardDao dao = new BoardDao();
		List<BoardDto> pageBoardList = dao.getPageBoard(10 * (page - 1) + 1, 10 * page);

		req.setAttribute("boardList", pageBoardList);
		req.setAttribute("page", page);
		req.setAttribute("navNum", (int) Math.floor((page - 1) / 10.0));

		req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
	}
}
