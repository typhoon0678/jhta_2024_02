package com.typhoon0678.board_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {

    private String title;
    private String content;
}
