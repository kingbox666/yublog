package com.yunblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.Blog;
import com.yunblog.entiy.BlogQuery;
import com.yunblog.entiy.BlogTag;
import com.yunblog.entiy.User;
import com.yunblog.mapper.BlogTagMapper;
import com.yunblog.service.BlogService;
import com.yunblog.service.TagService;
import com.yunblog.service.TypeService;
import com.yunblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: huang
 * @Date: 2021/09/13/15:07
 * @Description:
 */
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    TypeService typeService;

    @Autowired
    UserService userService;

    @Autowired
    BlogTagMapper blogTagMapper;

    /**
     * 主页展示
     * @param blog
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping({"/blog", "/"})
    public String index(BlogQuery blog, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> lists = blogService.listBlogIndex(blog);
        PageInfo<BlogQuery> blogPageInfo = new PageInfo<>(lists);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("tags", tagService.listTagTop(7));
        model.addAttribute("types",typeService.listTypeTop(7));
        model.addAttribute("hotBlogs", blogService.listBlogIndexHot(blog));
        return "index";
    }

    @RequestMapping({"/blog/page"})
    public String Index(BlogQuery blog, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "8") int pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> lists = blogService.listBlogIndex(blog);
        PageInfo<BlogQuery> blogPageInfo = new PageInfo<>(lists);
        model.addAttribute("blogs", blogPageInfo);
        model.addAttribute("tags", tagService.listTagTop(7));
        model.addAttribute("types",typeService.listTypeTop(7));
        model.addAttribute("hotBlogs", blogService.listBlogIndexHot(blog));
        return "index :: indexList";
    }


//    @RequestMapping("/blogTypes/{id}")
//    public String searchType(@PathVariable Long id , BlogQuery blog, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "6") int pageSize, Model model){
//        List<BlogQuery> list = blogService.listBlogType(id);
//        PageHelper.startPage(pageNum,pageSize);
//        PageInfo<BlogQuery> blogpageInfo = new PageInfo<>(list);
//        model.addAttribute("blogs",blogpageInfo);
//        model.addAttribute("tags", tagService.listTagTop(5));
//        model.addAttribute("types",typeService.listTypeTop(7));
//        model.addAttribute("hotBlogs", blogService.listBlogIndexHot(blog));
//        return "index";
//    }


    /**
     * 全局搜索
     * @param pageNum
     * @param pageSize
     * @param model
     * @param query
     * @return
     */
    @RequestMapping("/blog/search")
    public String search(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5") int pageSize , Model model ,@RequestParam String query){
        PageHelper.startPage(pageNum,pageSize);
        List<BlogQuery> list = blogService.listBlogSearch(query);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }
}
