package com.typhoon0678.board_test.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @NotBlank(message = "Write username")
    private String username;
    @NotBlank(message = "Write password")
    private String password;
}
