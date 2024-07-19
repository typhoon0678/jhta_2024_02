package com.typhoon0678.board_practice.service;

import com.typhoon0678.board_practice.dto.CustomUserDetails;
import com.typhoon0678.board_practice.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Member member = memberService.login(username);
            return new CustomUserDetails(member, null);

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }

    }
}
