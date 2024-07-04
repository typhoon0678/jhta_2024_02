package com.typhoon0678.ch01.controller;

import com.typhoon0678.ch01.dto.Member;
import com.typhoon0678.ch01.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(
            @Valid Member member, Errors errors, Model model) {

        if (errors.hasErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("member", member);
            return "signin";
        }

        memberService.saveMember(member);
        return "redirect:/member/list";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("memberList", memberList);
        return "list";
    }
}
