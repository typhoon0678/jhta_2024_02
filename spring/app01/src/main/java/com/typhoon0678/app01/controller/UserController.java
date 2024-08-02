package com.typhoon0678.app01.controller;

import com.typhoon0678.app01.request.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RequestMapping 어노테이션에서 Target TYPE, METHOD : 클래스, 메서드에 모두 부착 가능함을 나타냄
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/register")
    public String form() {
        return "form";
    }

    /**
     * 요청핸들러 메소드의 매개변수 처리하기
     * Model model -> 뷰에 전달할 값을 담을 Model 객체를 생성해서 주입
     * 사용자 정의, 기본 정의 클래스 or 기본 자료형 -> 요청 파라미터 값을 담을 객체
     * ---
     * 요청 파라미터 이름과 변수명이 같은 경우, @RequestParam 생략 가능
     * ---
     * return 값이 String 인 경우 default 로 View Name 으로 인식
     * String 이 아닌 경우, @Mapping 이름(register)을 View Name 으로 인식
     */

    @PostMapping("/register")
    public String register(UserForm userForm) {


        return "redirect:/";
    }
}
