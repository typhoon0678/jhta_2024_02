package com.typhoon0678.board_practice.dto;

import com.typhoon0678.board_practice.entity.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;
    private String username;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .username(comment.getMember().getUsername())
                .build();
    }
}
