package com.typhoon0678.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

    private int id;
    @NotBlank(message = "Write title")
    private String title;
    @NotBlank(message = "Write content")
    private String content;

    @Builder
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
