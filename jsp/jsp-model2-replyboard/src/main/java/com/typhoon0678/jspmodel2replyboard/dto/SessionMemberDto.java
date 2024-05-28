package com.typhoon0678.jspmodel2replyboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionMemberDto {

    private int userNo;
    private String userID;
    private String userName;
    private String email;
    private String postcode;
    private String address;
    private String detailAddress;
    private String grade;
    private String birth;
    private String originalProfile;
    private String renameProfile;
}
