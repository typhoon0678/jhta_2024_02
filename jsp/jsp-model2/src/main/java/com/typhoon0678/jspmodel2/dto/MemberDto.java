package com.typhoon0678.jspmodel2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private int userNo;
    private String userID;
    private String userPW;
    private String userName;
    private String email;
    private String postcode;
    private String address;
    private String detailAddress;
    private String grade;
    private String birth;
    private String originalProfile;
    private String renameProfile;

    public String[] getMember() {
        return new String[]{userID, userPW, userName, email, postcode, address, detailAddress, grade, birth, originalProfile, renameProfile};
    }
}
