package com.typhoon0678.first.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String userId;
    private String userPw;
    private LocalDate birth;
}
