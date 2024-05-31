package com.typhoon0678.mybatis.controller.member;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

import net.coobird.thumbnailator.Thumbnails;

import com.typhoon0678.mybatis.dao.MemberDao;
import com.typhoon0678.mybatis.dto.Grade;
import com.typhoon0678.mybatis.dto.MemberDto;
import com.typhoon0678.mybatis.util.CustomAlert;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/member/insert")
@MultipartConfig
public class InsertMember extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/member/insert-member.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        String originalProfile = "";
        String renameProfile = "";
        String uploadUrl;

        String folderDir = "/images";
        String uploadDir = getServletConfig().getServletContext().getRealPath(folderDir);
        Files.createDirectories(Paths.get(uploadDir));

        Part profile = req.getPart("profile");
        String partHeader = profile.getHeader("Content-Disposition");
        String[] partArray = partHeader.split("filename=");
        String originalFileName = partArray[1].trim().replace("\"", "");


        if (!originalFileName.isEmpty()) {
            String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String formatNow = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("_yyyyMMdd_hhmmss")
            );

            originalProfile = folderDir + File.separator + fileName + extension;
            renameProfile = folderDir + File.separator + fileName + formatNow + extension;

            uploadUrl = uploadDir + File.separator + fileName + formatNow + extension;
            profile.write(uploadUrl);
            Thumbnails.of(uploadUrl)
                .size(100, 200)
                .toFile(uploadUrl);
        }

        String userPW = BCrypt.hashpw(req.getParameter("userPW"), BCrypt.gensalt());

        MemberDto memberDto = MemberDto.builder()
                .userID(req.getParameter("userID"))
                .userName(req.getParameter("userName"))
                .userPW(userPW)
                .email(req.getParameter("userEmail"))
                .postcode(req.getParameter("userPostCode"))
                .address(req.getParameter("userAddress"))
                .detailAddress(req.getParameter("userDetailAddress"))
                .grade(Grade.MEMBER)
                .birth(req.getParameter("userBirth"))
                .originalProfile(originalProfile)
                .renameProfile(renameProfile)
                .build();

        MemberDao dao = new MemberDao();

        int result = dao.insertMember(memberDto);
        dao.close();
        if (result > 0) {
            CustomAlert.setAlert(req, resp, "Member Created", "/index");

        } else {
            CustomAlert.setErrorAlert(req, resp, "Error inserting member", "/member/insert-member.jsp");

        }

    }
}
