package com.hugh.authentication.sso.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@Slf4j
public class AdminController {

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String index(ModelMap modelMap) {
        return "/login";
    }
}
