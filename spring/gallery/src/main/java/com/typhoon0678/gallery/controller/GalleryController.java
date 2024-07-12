package com.typhoon0678.gallery.controller;

import com.typhoon0678.gallery.dao.GalleryDao;
import com.typhoon0678.gallery.dto.GalleryDto;
import com.typhoon0678.gallery.enums.Category;
import com.typhoon0678.gallery.service.GalleryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    private final Category[] categoryArray =
            {Category.PAINT, Category.PHOTO, Category.SKETCH};
    List<Category> categoryList = Arrays.asList(categoryArray);

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("galleryDto", new GalleryDto());

        return "gallery/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute @Valid GalleryDto galleryDto,
                        BindingResult bindingResult, Model model) {
        if (galleryDto.getFile().isEmpty()) {
            new FieldError("fileError", "file", "Please select Image");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryList);
        }

        if (bindingResult.hasErrors() || galleryDto.getFile().isEmpty()) {
            return "gallery/write";
        }

        int result = galleryService.insertGallery(galleryDto);

        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<GalleryDto> galleryDtoList = galleryService.getAllGallery();
        model.addAttribute("galleryDtoList", galleryDtoList);

        return "gallery/list";
    }

    @GetMapping("/json-list")
    @ResponseBody
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<GalleryDto> jsonList(Model model) {
        return galleryService.getAllGallery();
    }

}
