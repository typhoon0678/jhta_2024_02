package com.typhoon0678.question.controller;

import com.typhoon0678.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/insert")
    public String insert() {
        return "question/insert";
    }

    @PostMapping("/insert")
    public String insertSubmit(@RequestParam String subject, @RequestParam String content) {
        questionService.insert(subject, content);

        return "redirect:/";
    }
}
