package com.typhoon0678.ch01.controller;

import com.typhoon0678.ch01.dto.Member;
import com.typhoon0678.ch01.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test() {
        testService.test();
        return "redirect:/";
    }

    @GetMapping("/member")
    @ResponseBody
    public Member responseMember() {

        return Member.builder()
                .userName("username")
                .userPw("1234")
                .userId("userId")
                .build();
    }
}
