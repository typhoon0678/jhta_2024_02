package com.typhoon0678.mybatis.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import net.coobird.thumbnailator.Thumbnails;

import com.typhoon0678.mybatis.dto.ProfileDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class ThumbnailFactory {

	public static ProfileDto createThumbnail(Part profile, String uploadDir, String userID) throws IOException {
		String originalProfile = "";
		String renameProfile = "";
		String uploadUrl;

		String folderDir = "/images";
		Files.createDirectories(Paths.get(uploadDir));

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

		return ProfileDto.builder()
			.originalProfile(originalProfile)
			.renameProfile(renameProfile)
			.userID(userID)
			.build();
	}
}
