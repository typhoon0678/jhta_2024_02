package com.typhoon0678.board_test2.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private String username;
    private String password;
    private String role;
}
