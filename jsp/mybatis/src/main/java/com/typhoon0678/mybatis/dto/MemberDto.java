package com.typhoon0678.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Grade grade;
    private String birth;
    private String originalProfile;
    private String renameProfile;

    public String[] getMember() {
        return new String[]{userID, userPW, userName, email, postcode, address, detailAddress, grade.toString(), birth, originalProfile, renameProfile};
    }
}
