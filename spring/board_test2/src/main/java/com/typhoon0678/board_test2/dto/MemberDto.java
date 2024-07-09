package com.typhoon0678.board_test2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String username;
    private String password;
    private String role;
}
