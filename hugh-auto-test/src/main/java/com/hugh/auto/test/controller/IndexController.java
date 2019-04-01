package com.hugh.auto.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/autoTest")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
