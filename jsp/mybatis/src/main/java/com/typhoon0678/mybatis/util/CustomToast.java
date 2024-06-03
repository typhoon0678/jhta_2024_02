package com.typhoon0678.mybatis.util;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomToast {

	public static void setToast(HttpServletRequest req, HttpServletResponse resp, String msg, String jsp) throws
		ServletException,
		IOException {

		req.setAttribute("toastMsg", msg);

		req.getRequestDispatcher("/WEB-INF/" + jsp).forward(req, resp);
	}
}
