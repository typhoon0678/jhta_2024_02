package com.typhoon0678.mybatis.controller.board;

import java.io.IOException;
import java.util.List;

import com.typhoon0678.mybatis.dao.BoardDao;
import com.typhoon0678.mybatis.dto.BoardDto;
import com.typhoon0678.mybatis.dto.PageDto;
import com.typhoon0678.mybatis.dto.SearchDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/list")
public class ListBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pageStr = req.getParameter("page");
		String search = req.getParameter("search");
		String searchWord = req.getParameter("searchWord");

		int page = (pageStr == null ? 1 : Integer.parseInt(pageStr));
		int perPage = 10;
		int start = perPage * (page - 1) + 1;
		int end = perPage * page;

		BoardDao dao = new BoardDao();

		int total;
		List<BoardDto> boardList;

		if (search == null || search.isBlank()) {
			total = dao.getTotal();

			boardList = dao.getBoardList(new PageDto(start, end));

		} else {
			SearchDto searchDto = SearchDto.builder()
				.search(search)
				.searchWord(searchWord)
				.start(start)
				.end(end)
				.build();

			total = dao.getSearchTotal(searchDto);

			boardList = dao.getSearchBoardList(searchDto);

			req.setAttribute("search", search);
			req.setAttribute("searchWord", searchWord);
		}

		dao.close();

		req.setAttribute("boardList", boardList);
		req.setAttribute("page", page);
		req.setAttribute("perPage", perPage);
		req.setAttribute("total", total);

		req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
	}
}
