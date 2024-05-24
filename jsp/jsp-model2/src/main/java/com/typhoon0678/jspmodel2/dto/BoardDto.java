package com.typhoon0678.jspmodel2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private int boardNo;
    private String subject;
    private String content;
    private String userID;
    private String userName;
    private String regDate;
    private int hit;

}
