package com.typhoon0678.mybatis.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.typhoon0678.mybatis.dao.BoardDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/delete-all")
public class DeleteBoard extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();

		String[] tempArray = req.getParameterValues("check");

		int[] noArray = new int[tempArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			noArray[i] = Integer.parseInt(tempArray[i]);
		}

		BoardDao dao = new BoardDao(false);

		Map<String, Integer> checkMap = new HashMap<>();
		checkMap.put("result", dao.deleteAllBoard(noArray));
		dao.close();

		Gson gson = new Gson();
		out.println(gson.toJson(checkMap));

		out.flush();
	}
}
