package com.typhoon0678.app01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller 어노테이션 역할
 * - spring mvc 에서 Controller 를 담당함을 알림
 * - Application 시작 시, @ComponentScan 에 탐색된다.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("message", "Hello World!");

        return "index";
    }
}
