package com.typhoon0678.board_test.service;

import com.typhoon0678.board_test.dto.CustomUserDetails;
import com.typhoon0678.board_test.dto.MemberDto;
import com.typhoon0678.board_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memberDto = memberRepository.findByUsername(username);
        if (memberDto == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(memberDto);
    }
}
