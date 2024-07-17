package com.typhoon0678.board_practice.service;

import com.typhoon0678.board_practice.dto.CommentResponseDto;
import com.typhoon0678.board_practice.entity.Comment;
import com.typhoon0678.board_practice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void write(Comment comment) {
        commentRepository.save(comment);
    }

    public List<CommentResponseDto> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }
}
