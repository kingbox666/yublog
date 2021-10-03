package com.yunblog.controller;

import com.yunblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;

/**
 *
 * @Author: huang
 * @Date: 2021/09/08/0:43
 * @Description: 后台控制器
 */
@Controller

public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/adminLogin")
    public String login(){
        return "admin/login";
    }

    /**
     * 登录验证方法
     * @param username
     * @param password
     * @param httpSession
     * @param redirectAttributes
     * @return  需要跳转的页面
     */
    @RequestMapping("/tologin")
    public String toLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession httpSession, RedirectAttributes redirectAttributes){
        //获取当前用户数据
        Subject subject = SecurityUtils.getSubject();
        //封装登录用户数据
        UsernamePasswordToken Token = new UsernamePasswordToken(username, password);

        try{

            subject.login(Token); //执行登录方法
        }catch (UnknownAccountException e){  //用户名错误异常
            redirectAttributes.addFlashAttribute("msg","用户名错误");
            return "redirect:/adminLogin";
        }catch (IncorrectCredentialsException e){  //密码错误异常
            redirectAttributes.addFlashAttribute("msg","密码错误");
            return "redirect:/adminLogin";
        }
        return "admin/index";
    }

    /**
     *
     * @param httpSession
     * @return 重定向的页面
     */
    @RequestMapping("/logout")
    public String logOut(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/adminLogin";
    }

    @RequestMapping("/back")
    public String back(){
        return "redirect:/blog";
    }
}
