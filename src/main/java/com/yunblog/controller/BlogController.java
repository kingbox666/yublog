package com.yunblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.*;
import com.yunblog.service.BlogService;
import com.yunblog.service.TagService;
import com.yunblog.service.TypeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: huang
 * @Date: 2021/09/08/23:14
 * @Description:
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @RequestMapping("/input")
    public String toInput(Model model) {
        model.addAttribute("types", typeService.PageType());
        model.addAttribute("tags", tagService.PageTag());
        model.addAttribute("blog", new Blog());
        return "admin/input";
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/blogs")
    public String toBlog(Blog blog, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> blogs = blogService.getAllBlog();
        System.out.println(blogs);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", typeService.PageType());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blog", blogs);
        return "admin/blogs";
    }

    /**
     * 查询
     *
     * @param searchBlog
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/blogs/search")
    public String toBlogs(SearchBlog searchBlog, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<BlogQuery> blogs = blogService.getBlogBySearch(searchBlog);
        System.out.println(blogs);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", typeService.PageType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }

    /**
     * 保存/新增
     * @param blog
     * @param attributes
     * @return
     */
    @RequestMapping("/blogs/saveblog")
    public String Input(Blog blog,  RedirectAttributes attributes) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println(user);
        // 获取当前用户
        if (user != null) {
            blog.setUser(user);
            blog.setType(typeService.getType(blog.getType().getId()));
            blog.setTages(tagService.listTag(blog.getTagIds()));
            int save = blogService.saveBlog(blog);
            if (save > 0) {
                attributes.addFlashAttribute("message", "添加文章成功");
            } else {
                attributes.addFlashAttribute("message", "添加文章失败");
            }
            return "redirect:/admin/blogs";
        } else {
            return "admin/index";
        }
    }

    /**
     * 更新跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/blogs/{id}/update")
    public String editBlog(@PathVariable long id, Model model) {
        model.addAttribute("types", typeService.PageType());
        model.addAttribute("tags", tagService.listTag());
        Blog blog = blogService.getBlogByID(id);
        model.addAttribute("blog", blog);
        return "admin/input";
    }

    /**
     * 编辑更新
     * @param blog
     * @param attributes
     * @return
     */
    @RequestMapping("/updateBlogs")
    public String updateBlogs(Blog blog, RedirectAttributes attributes) {
        blog.setUser( (User) SecurityUtils.getSubject().getPrincipal());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTages(tagService.listTag(blog.getTagIds()));
        int update = blogService.updateBlog(blog);
        if (update > 0) {
            attributes.addFlashAttribute("message", "操作成功");

        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/admin/blogs";
    }

    /**
     * 删除
     * @param id
     * @param attributes
     * @return
     */
    @RequestMapping("/blogs/{id}/delete")
    public String del(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
