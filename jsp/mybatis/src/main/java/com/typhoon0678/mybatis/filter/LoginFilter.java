package com.typhoon0678.mybatis.filter;

import java.io.IOException;

import com.typhoon0678.mybatis.dto.ModalDto;
import com.typhoon0678.mybatis.util.CustomAlert;
import com.typhoon0678.mybatis.util.CustomModal;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/board/*", "/admin/*", "/member/list", "/member/info"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HttpServletRequest req = (HttpServletRequest)servletRequest;
		HttpSession session = req.getSession();

		if (session.getAttribute("member") != null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			HttpServletResponse resp = (HttpServletResponse)servletResponse;

			// CustomAlert.setAlert(request, resp, "Login Required", "/member/login");

			ModalDto modalDto = ModalDto.builder()
				.title("Login Required")
				.msg("Please Login")
				.location("/member/login")
				.build();

			CustomModal.setModal(req, resp, modalDto);
		}
	}
}
