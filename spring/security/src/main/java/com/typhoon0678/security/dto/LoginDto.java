package com.typhoon0678.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class LoginDto {

    private int id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public LoginDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
