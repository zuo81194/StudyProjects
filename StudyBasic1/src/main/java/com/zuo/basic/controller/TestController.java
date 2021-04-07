package com.zuo.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("testController")
public class TestController {
    @RequestMapping("testMothod")
    public String testMothod() {
        return "test";
    }
}
