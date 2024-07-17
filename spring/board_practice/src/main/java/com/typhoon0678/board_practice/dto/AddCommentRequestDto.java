package com.typhoon0678.board_practice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddCommentRequestDto {

    private final String content;
    private final Long boardId;
}
