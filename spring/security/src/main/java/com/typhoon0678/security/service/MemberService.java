package com.typhoon0678.security.service;

import com.typhoon0678.security.dao.MemberDao;
import com.typhoon0678.security.dto.LoginDto;
import com.typhoon0678.security.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int signup(LoginDto loginDto) {
        LoginDto verifyLoginDto = LoginDto.builder()
                .username(loginDto.getUsername())
                .password(bCryptPasswordEncoder.encode(loginDto.getPassword()))
                .role(Role.USER.getRole())
                .build();

        return memberDao.signup(verifyLoginDto);
    }
}
