package com.typhoon0678.jspmodel2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

    private int userNo;
    private String userID;
    private String userPW;
    private String userName;
    private String email;
    private String postcode;
    private String address;
    private String detailAddress;
    private String birth;

    public String[] getMember() {
        return new String[] { userID, userPW, userName, email, postcode, address, detailAddress };
    }
}
