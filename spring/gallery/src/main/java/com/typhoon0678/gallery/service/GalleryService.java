package com.typhoon0678.gallery.service;

import com.typhoon0678.gallery.dao.GalleryDao;
import com.typhoon0678.gallery.dto.GalleryDto;
import com.typhoon0678.gallery.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    @Value("${file.path}")
    private String uploadPath;

    private final GalleryDao galleryDao;
    private final FileUtil fileUtil = new FileUtil();

    public int insertGallery(GalleryDto galleryDto) {
        String[] fileNames = fileUtil.getImageName(galleryDto.getFile());
        String originalTitle = fileNames[0];
        String renameTitle = fileNames[1];

        galleryDto.setOriginalTitle(originalTitle);
        galleryDto.setRenameTitle(renameTitle);

        boolean result = fileUtil.saveImage(
                galleryDto.getFile(),
                uploadPath,
                renameTitle);

        if (result) {
            return galleryDao.insertGallery(galleryDto);
        }

        return 0;
    }

    public List<GalleryDto> getAllGallery() {
        return galleryDao.getAllGallery();
    }
}
