package com.typhoon0678.gallery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GalleryDto {

    private int id;

    @NotBlank(message = "Please write title")
    private String title;

    @NotBlank(message = "Please write description")
    @Size(max = 1000, message = "Please write 1,000 characters or less")
    private String description;

    @NotBlank(message = "Please write category")
    private String category;

    private MultipartFile file;

    private String originalTitle;

    private String renameTitle;

    @Range(min = 0, max = 5, message = "Please Write between 0 and 5")
    private double point;

    private LocalDateTime regDate;

    @Builder
    public GalleryDto(String title, String description, String category, MultipartFile file, String originalTitle, double point) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.file = file;
        this.originalTitle = originalTitle;
        this.point = point;
    }
}
