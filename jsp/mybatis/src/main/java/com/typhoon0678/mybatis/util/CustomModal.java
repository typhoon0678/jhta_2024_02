package com.typhoon0678.mybatis.util;

import java.io.IOException;

import com.typhoon0678.mybatis.dto.ModalDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomModal {

	public static void setModal(HttpServletRequest req, HttpServletResponse resp, ModalDto modalDto) throws
		ServletException,
		IOException {

		req.setAttribute("modal", modalDto);

		req.getRequestDispatcher("/WEB-INF/modal-login.jsp").forward(req, resp);
	}
}
