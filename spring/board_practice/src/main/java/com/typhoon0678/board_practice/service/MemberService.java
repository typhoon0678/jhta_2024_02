package com.typhoon0678.board_practice.service;

import com.typhoon0678.board_practice.dto.LoginDto;
import com.typhoon0678.board_practice.entity.Member;
import com.typhoon0678.board_practice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(LoginDto loginDto) {
        memberRepository.save(Member.builder()
                .username(loginDto.getUsername())
                .password(passwordEncoder.encode(loginDto.getPassword()))
                .build());
    }

    public Member login(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found")
        );
    }

    public void saveMemberIfNotExist(Member member) {
        if (memberRepository.findByUsername(member.getUsername()).isEmpty()) {
            memberRepository.save(member);
        }
    }

}
