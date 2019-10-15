package com.kudla.chatappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @GetMapping("/test")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
