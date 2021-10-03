package com.yunblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: huang
 * @Date: 2021/09/22/17:07
 * @Description:
 */
@Controller
public class AboutShowController {

    /**
     * 跳转about
     * @return
     */
    @RequestMapping("/about")
    public String toAbout(){
        return "about";
    }
}
