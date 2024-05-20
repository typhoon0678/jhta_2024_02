package com.typhoon0678.jsp05.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private int no;
    private String subject;
    private String content;
    private String userID;
    private String userName;
    private String regDate;
    private int hit;
}