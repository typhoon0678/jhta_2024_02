package com.typhoon0678.board_practice.controller;

import com.typhoon0678.board_practice.dto.LoginDto;
import com.typhoon0678.board_practice.entity.Member;
import com.typhoon0678.board_practice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute LoginDto loginDto) {
        memberService.signup(loginDto);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginDto loginDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getUsername().equals(loginDto.getUsername())) {
            return "redirect:/";
        }

        return "redirect:/login";
    }

}
