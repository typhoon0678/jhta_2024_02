package com.typhoon0678.member.controller;

import com.typhoon0678.member.dto.Member;
import com.typhoon0678.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@Slf4j
public class MemberController {

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(
            @ModelAttribute Member member,
            RedirectAttributes redirectAttributes) {

        MemberService memberService = new MemberService();
        memberService.saveMember(member);

        redirectAttributes.addFlashAttribute("memberStore", MemberService.memberStore);

        return "redirect:/list/";
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }
}
