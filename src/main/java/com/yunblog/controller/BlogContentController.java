package com.yunblog.controller;

import com.yunblog.entiy.Blog;
import com.yunblog.entiy.Comment;
import com.yunblog.exception.NotFoundException;
import com.yunblog.mapper.BlogTagMapper;
import com.yunblog.mapper.CommentMapper;
import com.yunblog.service.BlogService;
import com.yunblog.service.CommentService;
import com.yunblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: huang
 * @Date: 2021/09/13/15:38
 * @Description:
 */
@Controller
public class BlogContentController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private CommentService commentService;

    /**
     * 显示指定博客详情信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping({"/blog/{id}"})
    public String blogDetail(
            @PathVariable long id,
            Model model) {

        Blog blog = blogService.getBlogIndex(id);
        if (blog == null){
            throw new NotFoundException("很抱歉，这篇博客不存在~");
        }
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("tags", tagService.listTag(blogTagMapper.selectByBlogId(id)));
        model.addAttribute("comments", comments);
        return "blog";
    }
}
