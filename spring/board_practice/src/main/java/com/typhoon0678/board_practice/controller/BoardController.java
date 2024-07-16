package com.typhoon0678.board_practice.controller;

import com.typhoon0678.board_practice.dto.BoardDto;
import com.typhoon0678.board_practice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/board/write")
    public String write(@ModelAttribute BoardDto boardDto,
                        Principal principal) {
        String username = principal.getName();
        boardService.write(boardDto, username);

        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("boardList", boardService.getBoardIndex());

        return "index";
    }
}
