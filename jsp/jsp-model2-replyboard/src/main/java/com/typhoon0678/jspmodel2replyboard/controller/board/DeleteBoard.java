package com.typhoon0678.jspmodel2replyboard.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.typhoon0678.jspmodel2replyboard.dao.BoardDao;
import com.typhoon0678.jspmodel2replyboard.dto.BoardDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class DeleteBoard extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String no = req.getParameter("no");

		BoardDao dao = new BoardDao();

		Map<String, Boolean> checkMap = new HashMap<>();
		checkMap.put("success", dao.updateBoardAvailable(no));

		Gson gson = new Gson();
		out.println(gson.toJson(checkMap));

		out.flush();
	}
}
