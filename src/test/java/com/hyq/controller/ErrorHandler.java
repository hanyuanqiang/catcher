package com.hyq.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by genius on 2017/3/11.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ModelAndView error(Exception ex){
        ModelAndView mav = new ModelAndView();
        System.out.println(ex.getMessage());
        mav.addObject("name","发生错误");
        mav.addObject("age","0");
        mav.setViewName("hello");
        return mav;
    }

}
