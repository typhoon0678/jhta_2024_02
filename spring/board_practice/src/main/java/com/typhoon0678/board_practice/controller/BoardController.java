package com.typhoon0678.board_practice.controller;

import com.typhoon0678.board_practice.dto.BoardDetailResponseDto;
import com.typhoon0678.board_practice.dto.BoardDto;
import com.typhoon0678.board_practice.dto.CustomUserDetails;
import com.typhoon0678.board_practice.entity.Board;
import com.typhoon0678.board_practice.service.BoardService;
import com.typhoon0678.board_practice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/board/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/board/write")
    public String write(@ModelAttribute BoardDto boardDto,
                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boardService.write(Board.toEntity(boardDto, customUserDetails.getMember()));

        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("boardList", boardService.getBoardIndex());

        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);

        model.addAttribute("board", BoardDetailResponseDto.from(board));
        model.addAttribute("commentList", commentService.getCommentsByBoardId(id));

        return "board/detail";
    }
}
