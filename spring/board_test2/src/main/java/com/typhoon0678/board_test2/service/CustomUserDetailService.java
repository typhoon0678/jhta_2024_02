package com.typhoon0678.board_test2.service;

import com.typhoon0678.board_test2.dao.MemberDao;
import com.typhoon0678.board_test2.dto.CustomUserDetails;
import com.typhoon0678.board_test2.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDto memberDto = memberDao.login(username);
        if (memberDto != null) {
            return new CustomUserDetails(memberDto);
        }
        return null;
    }
}
