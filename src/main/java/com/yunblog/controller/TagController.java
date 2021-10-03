package com.yunblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunblog.entiy.Tag;
import com.yunblog.entiy.User;
import com.yunblog.service.TagService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @Author: huang
 * @Date: 2021/09/08/0:43
 * @Description: 标签控制器
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/tag")
    public String toTag( @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5")int pageSize , Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> list = tagService.PageTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        model.addAttribute("page",pageInfo);
        model.addAttribute("tag",list);
        return "admin/tags";
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/tag/search")
    public String toTags( @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5")int pageSize , Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> list = tagService.PageTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        model.addAttribute("page",pageInfo);
        model.addAttribute("tag",list);
        return "admin/tags ::tagList";
    }

    /**
     * 新增跳转
     * @param model
     * @return
     */
    @RequestMapping("/tag/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    /**
     * 新增
     * @param tag
     * @param attributes
     * @return
     */
    @RequestMapping("/saveTag")
    public String saveTag(@Valid Tag tag, RedirectAttributes attributes){
        Tag byName = tagService.getByName(tag.getName());
        if (byName != null){
            attributes.addFlashAttribute("msg","不能添加重复标签");
            return "redirect:/admin/tag/input";
        }
        int i = tagService.saveTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message","添加失败");
        }else{
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tag";
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/tag/delete/{id}")
    public String del(@PathVariable("id") Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tag";
    }

    /**
     * tag id传参 跳转
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tag/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tag-update";
    }

    /**
     * 更新
     * @param tag
     * @param id
     * @param attributes
     * @param model
     * @return
     */
    @RequestMapping("/tag/{id}/update")
    public String updateTag(@Valid Tag tag,@PathVariable("id") Long id, RedirectAttributes attributes, Model model){
        Tag byName = tagService.getByName(tag.getName());
        if (byName != null){
            attributes.addFlashAttribute("msg","不能添加重复标签");
            return "redirect:/admin/tag/input";
        }
        int i = tagService.updateTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tag";
    }
}
