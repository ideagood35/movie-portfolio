package com.example.movieportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class MainController {

    @GetMapping("/")
    public String test() {
        return "index";
    }
    //회원가입 페이지 이동
    @GetMapping("/mainView.reservation")
    public String main() {
        return "mainView";
    }
}

