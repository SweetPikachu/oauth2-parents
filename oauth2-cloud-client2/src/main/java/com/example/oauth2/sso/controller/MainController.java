package com.example.oauth2.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {




    @GetMapping("/")
    public String member() {
        return "member";
    }

}
