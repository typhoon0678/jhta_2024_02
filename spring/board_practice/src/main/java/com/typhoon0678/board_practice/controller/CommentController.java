package com.typhoon0678.board_practice.controller;

import com.typhoon0678.board_practice.dto.AddCommentRequestDto;
import com.typhoon0678.board_practice.dto.CustomUserDetails;
import com.typhoon0678.board_practice.entity.Board;
import com.typhoon0678.board_practice.entity.Comment;
import com.typhoon0678.board_practice.service.BoardService;
import com.typhoon0678.board_practice.service.CommentService;
import com.typhoon0678.board_practice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/comment/write")
    public String addComment(@ModelAttribute AddCommentRequestDto requestDto,
                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Board board = boardService.getBoardById(requestDto.getBoardId());

        commentService.write(Comment.toEntity(requestDto, board, customUserDetails.getMember()));

        return "redirect:/board/" + board.getId();
    }
}
