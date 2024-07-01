package com.typhoon0678.first.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("message", "<h2>Hello World</h2>");
        return "test";
    }

    @GetMapping("/test/user")
    @ResponseBody
    public String testUser() {
        return "<h2>Hello /test/user</h2>";
    }

    @GetMapping("/test/member")
    public String testMember(@RequestParam(required = false) String name, @RequestParam(required = false) int age, Model model) {
        model.addAttribute("name", (!name.isEmpty()) ? name : "name");

        log.info("name : {}", name);
        return "testMember";
    }

}
