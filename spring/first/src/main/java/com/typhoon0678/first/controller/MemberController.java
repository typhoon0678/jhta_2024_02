package com.typhoon0678.first.controller;

import com.typhoon0678.first.dto.Member;
import com.typhoon0678.first.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class MemberController {

    public static List<User> userList = new ArrayList<>();

    @GetMapping("/member")
    public String member(Model model) {

        List<Member> memberList = new ArrayList<>();
        memberList.add(Member.builder()
                .name("testName")
                .age(20)
                .build());
        memberList.add(Member.builder()
                .name("testName2")
                .age(21)
                .build());
        memberList.add(Member.builder()
                .name("testName3")
                .age(22)
                .build());

        model.addAttribute("memberList", memberList);

        return "member";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String userId,
            @RequestParam String userPw,
            @RequestParam(required = false) LocalDate birth) {
        userList.add(User.builder()
                .userId(userId)
                .userPw(userPw)
                .birth(birth)
                .build());

        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("userList", userList);

        return "list";
    }
}
