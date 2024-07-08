package com.typhoon0678.board_test.controller;

import com.typhoon0678.board_test.domain.Board;
import com.typhoon0678.board_test.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {

        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);

        return "index";
    }
}
