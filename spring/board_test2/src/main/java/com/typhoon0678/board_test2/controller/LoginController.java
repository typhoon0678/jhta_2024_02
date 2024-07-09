package com.typhoon0678.board_test2.controller;

import com.typhoon0678.board_test2.dao.MemberDao;
import com.typhoon0678.board_test2.dto.MemberDto;
import com.typhoon0678.board_test2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;
    private final MemberDao memberDao;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto) {
        MemberDto loginMemberDto = memberService.login(memberDto.getUsername());
        log.info("loginMemberDto: {}", loginMemberDto);
        if (loginMemberDto == null) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDto memberDto) {
        int result = memberService.signup(memberDto);
        log.info("-----------------------test---------------------------");
        log.warn("result is {}", result);
        System.out.println("result : " + result);
        if (result == 0) {
            return "redirect:/signup";
        }
        return "redirect:/login";
    }

    @PostMapping("/duplicate")
    @ResponseBody
    public ResponseEntity<String> duplicate(@RequestParam String username) {
        if (memberDao.duplicate(username) > 0) {
            return new ResponseEntity<>("duplicated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
