package com.typhoon0678.member.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    private int idx;
    private String userId;
    private String userName;
    private String password;
    private LocalDate birth;
}
