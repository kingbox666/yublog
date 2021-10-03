package com.yunblog.controller;

import com.yunblog.entiy.BlogQuery;
import com.yunblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: huang
 * @Date: 2021/09/21/14:51
 * @Description:
 */
@Controller
public class ArchivesShowController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/archives")
    public String toArchives(Model model){
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "archives";
    }
}
