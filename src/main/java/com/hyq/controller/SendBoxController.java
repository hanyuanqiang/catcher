package com.hyq.controller;

import com.hyq.entity.Bug;
import com.hyq.entity.SendBox;
import com.hyq.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by genius on 2017/3/14.
 */
@Controller
@RequestMapping("/sendBox")
public class SendBoxController {

    @Resource
    private MyService myService;



}
