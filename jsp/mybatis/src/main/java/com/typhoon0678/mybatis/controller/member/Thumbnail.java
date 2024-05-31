package com.typhoon0678.mybatis.controller.member;

import java.io.IOException;

import com.typhoon0678.mybatis.dao.MemberDao;
import com.typhoon0678.mybatis.dto.ProfileDto;
import com.typhoon0678.mybatis.dto.SessionMemberDto;
import com.typhoon0678.mybatis.util.CookieManager;
import com.typhoon0678.mybatis.util.CustomAlert;
import com.typhoon0678.mybatis.util.ThumbnailFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/member/thumbnail")
public class Thumbnail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/member/thumbnail.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		SessionMemberDto dto = (SessionMemberDto) session.getAttribute("member");
		if (dto.getUserID() == null) {
			CustomAlert.setErrorAlert(req, resp, "Please Login Again", "/WEB-INF/member/thumbnail.jsp");
		}

		Part profile = req.getPart("profile");
		String folderDir = "/images";
		String uploadDir = getServletConfig().getServletContext().getRealPath(folderDir);

		ProfileDto profileDto = ThumbnailFactory.createThumbnail(profile, uploadDir, dto.getUserID());
		String originalProfile = profileDto.getOriginalProfile();
		String renameProfile = profileDto.getRenameProfile();

		MemberDao dao = new MemberDao();
		int result = dao.changeThumbnail(profileDto);
		dao.close();

		if (originalProfile != null && renameProfile != null && result > 0) {
			dto.setOriginalProfile(originalProfile);
			dto.setRenameProfile(renameProfile);
			session.setAttribute("member", dto);

			CustomAlert.setAlert(req, resp, "Thumbnail Changed", "/member/info");

		} else {
			CustomAlert.setErrorAlert(req, resp, "Something went wrong", "/member/thumbnail.jsp");
		}

	}
}

