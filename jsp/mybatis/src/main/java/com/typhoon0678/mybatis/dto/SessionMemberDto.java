package com.typhoon0678.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private Grade grade;
    private String birth;
    private String originalProfile;
    private String renameProfile;
}
