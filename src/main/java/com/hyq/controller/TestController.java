package com.hyq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by genius on 2017/3/16.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/froala_editor_html")
    public String test01(){
        return "redirect:/static/froala_editor/examples/basic.html";
    }

    @RequestMapping("/froala_editor_jsp")
    public String test02(){
        return "froala_editor";
    }

    @RequestMapping("/umditor_jsp")
    public String test03(){
        return "umditor";
    }
}
