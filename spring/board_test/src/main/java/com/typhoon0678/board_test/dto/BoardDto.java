package com.typhoon0678.board_test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    @NotBlank(message = "Write Title")
    private String title;
    private String content;
}
