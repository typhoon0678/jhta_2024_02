package com.typhoon0678.ch01.controller;

import com.typhoon0678.ch01.dto.Member;
import com.typhoon0678.ch01.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signin() {
        return "member/signin";
    }

    @PostMapping("/signin")
    public String signin(
            @ModelAttribute Member member,
            Model model) {
        log.info(member.toString());
        Member savedMember = memberService.saveMember(member);
        log.info(savedMember.toString());

        model.addAttribute("member", savedMember);

        return "redirect:/members";
    }

    @GetMapping("/members")
    public String member(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "member/members";
    }

    @GetMapping("/member/{idx}")
    public String member(@PathVariable int idx, Model model) {
        Member member = memberService.getMemberByIdx(idx);
        model.addAttribute("member", member);

        return "member/member";
    }
}
