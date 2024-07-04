package com.typhoon0678.board.controller;

import com.typhoon0678.board.dto.BoardDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }

    @PostMapping("/write")
    public String writeProcess(
            @Valid @ModelAttribute BoardDto boardDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        return "redirect:/";
    }
}
