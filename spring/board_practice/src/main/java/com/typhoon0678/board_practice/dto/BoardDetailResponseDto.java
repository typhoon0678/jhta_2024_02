package com.typhoon0678.board_practice.dto;

import com.typhoon0678.board_practice.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDetailResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;

    public static BoardDetailResponseDto from(Board board) {
        return BoardDetailResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getMember().getUsername())
                .build();
    }
}
