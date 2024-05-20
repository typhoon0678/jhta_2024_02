package com.typhoon0678.jsp05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {

    private int age;
    private String name;
    private String userID;
    private String userPW;
}
