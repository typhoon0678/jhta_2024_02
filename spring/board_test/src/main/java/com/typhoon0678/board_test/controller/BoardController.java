package com.typhoon0678.board_test.controller;

import com.typhoon0678.board_test.domain.Board;
import com.typhoon0678.board_test.dto.BoardDto;
import com.typhoon0678.board_test.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "writeBoard";
    }

    @PostMapping("/write")
    public String write(
            @Valid @ModelAttribute BoardDto boardDto,
            BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "writeBoard";
        }

        HttpSession session = request.getSession(false);

        boardService.save(Board.builder()
                .username((String) session.getAttribute("username"))
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build());
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {

        Board board = boardService.findById(id);
        if (board == null) {
            return "redirect:/";
        }

        model.addAttribute("board", board);
        return "detailBoard";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        boardService.delete(id);

        return ResponseEntity.ok().build();
    }
}
