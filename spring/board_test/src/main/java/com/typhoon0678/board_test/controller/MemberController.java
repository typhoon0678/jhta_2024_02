package com.typhoon0678.board_test.controller;

import com.typhoon0678.board_test.domain.Member;
import com.typhoon0678.board_test.dto.MemberDto;
import com.typhoon0678.board_test.service.CustomUserDetailService;
import com.typhoon0678.board_test.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final CustomUserDetailService customUserDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signin")
    public String signIn(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "signIn";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/signin")
    public String signin(
            @Valid @ModelAttribute MemberDto memberDto,
            BindingResult bindingResult,
            HttpServletRequest request) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));

        UserDetails userDetails = customUserDetailService.loadUserByUsername(memberDto.getUsername());

        if (bindingResult.hasErrors()) {
            return "signin";
        } else if (!userDetails.getPassword().equals(memberDto.getPassword())) {
            return "signin";
        }

//        HttpSession session = request.getSession();
//        session.setAttribute("username", memberDto.getUsername());

        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @ModelAttribute MemberDto memberDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        memberService.save(Member.builder()
                .username(memberDto.getUsername())
                .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                .build());

        return "redirect:/member/signin";
    }

    @GetMapping("/signout")
    public String signout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
