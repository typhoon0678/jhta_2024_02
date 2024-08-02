package com.typhoon0678.app01.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestUserController {

    @PostMapping("/user")
    public String register(@RequestParam Map<String, Object> map) {

        return "success";
    }
}
