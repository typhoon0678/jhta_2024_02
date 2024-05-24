package com.typhoon0678.jspmodel2.contoller.board;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/board/upload-image")
@MultipartConfig
public class UploadImageBoard extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part ckUploadedFile = req.getPart("upload");

        Gson gson = new Gson();
        Map<String, Object> jsonMap = new HashMap<>();

        String folderDir = "/images";
        String uploadDir = getServletConfig().getServletContext().getRealPath(folderDir);

        String partHeader = ckUploadedFile.getHeader("Content-Disposition");
        String[] partArray = partHeader.split("filename=");
        String originalFileName = partArray[1].trim().replace("\"", "");

        if (!originalFileName.isEmpty()) {
            String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String formatNow = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("_yyyyMMdd_HHmmss")
            );

            ckUploadedFile.write(uploadDir + File.separator + fileName + formatNow + extension);


            jsonMap.put("uploaded", true);
            jsonMap.put("url", req.getContextPath() + folderDir + File.separator + fileName + formatNow + extension);

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(gson.toJson(jsonMap));
        }

    }
}
