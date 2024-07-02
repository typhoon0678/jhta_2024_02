package com.typhoon0678.ch01.dto;

import lombok.*;

@Getter
@ToString
public class Member {

    @Setter
    private int idx;
    private final String userName;
    private final String userPw;
    private final String userId;


    @Builder
    public Member(String userName, String userPw, String userId) {
        this.userName = userName;
        this.userPw = userPw;
        this.userId = userId;
    }
}
