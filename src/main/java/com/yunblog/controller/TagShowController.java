package com.yunblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.BlogQuery;
import com.yunblog.entiy.Tag;
import com.yunblog.entiy.Type;
import com.yunblog.service.BlogService;
import com.yunblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: huang
 * @Date: 2021/09/15/11:31
 * @Description:
 */
@Controller
public class TagShowController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    /**
     * 主页以及标签的展示
     * @param pageNum
     * @param pageSize
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tags/{id}")
    public String getBlogTag(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "100") int pageSize, @PathVariable Long id, Model model){
        List<Tag> tags = tagService.PageTag();
        System.out.println("tags:"+tags);
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        PageHelper.startPage(pageNum,pageSize);
        List<BlogQuery> list = blogService.listBlogTag(id);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId", id);
        return "tag";
    }
}
