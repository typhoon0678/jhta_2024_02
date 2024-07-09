package com.typhoon0678.security.service;

import com.typhoon0678.security.dao.MemberDao;
import com.typhoon0678.security.dto.CustomUserDetails;
import com.typhoon0678.security.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginDto loginDto = memberDao.login(username);
        if (loginDto != null) {
            return new CustomUserDetails(loginDto);
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
