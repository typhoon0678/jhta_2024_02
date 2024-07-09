package com.typhoon0678.board_test2.service;

import com.typhoon0678.board_test2.dao.MemberDao;
import com.typhoon0678.board_test2.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int signup(MemberDto memberDto) {
        MemberDto signInDto = MemberDto.builder()
                .username(memberDto.getUsername())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .role("USER")
                .build();

        return memberDao.signup(signInDto);
    }

    public MemberDto login(String username) {
        return memberDao.login(username);
    }
}
