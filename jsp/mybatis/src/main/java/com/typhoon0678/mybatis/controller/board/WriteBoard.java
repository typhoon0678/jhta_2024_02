package com.typhoon0678.mybatis.controller.board;

import java.io.IOException;

import com.typhoon0678.mybatis.dao.BoardDao;
import com.typhoon0678.mybatis.dto.BoardDto;
import com.typhoon0678.mybatis.util.CustomAlert;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/write")
public class WriteBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String userName = req.getParameter("userName");
		String userID = req.getParameter("userID");

		BoardDao dao = new BoardDao();

		int reGroup = dao.getMaxReGroup() + 1;

		BoardDto boardDto = BoardDto.builder()
			.subject(subject)
			.content(content)
			.userName(userName)
			.userID(userID)
			.reGroup(reGroup)
			.build();

		int result = dao.writeBoard(boardDto);
		dao.close();

		if (result == 1) {
			CustomAlert.setAlert(req, resp, "Board Created", "/board/list");
		} else {
			CustomAlert.setErrorAlert(req, resp, "Something went wrong", "/WEB-INF/board/write.jsp");
		}
	}
}
