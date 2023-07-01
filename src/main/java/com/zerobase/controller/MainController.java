package com.zerobase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/main/partner")
    public String partner() {
        return "main/partner";
    }

    @RequestMapping("/main/user")
    public String user() {
        return "main/user";
    }
}