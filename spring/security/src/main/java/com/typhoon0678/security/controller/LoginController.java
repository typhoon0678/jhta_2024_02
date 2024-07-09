package com.typhoon0678.security.controller;

import com.typhoon0678.security.dto.LoginDto;
import com.typhoon0678.security.service.CustomUserDetailsService;
import com.typhoon0678.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute LoginDto loginDto) {
        if (memberService.signup(loginDto) == 0) {
            return "signup";
        }

        return "redirect:/login";
    }
}
