package com.typhoon0678.gallery.dao;

import com.typhoon0678.gallery.dto.GalleryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryDao {

    int insertGallery(GalleryDto galleryDto);
    List<GalleryDto> getAllGallery();
}
