package com.duc010298.phongkham158.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
