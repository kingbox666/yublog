package com.yunblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yunblog.entiy.Type;
import com.yunblog.entiy.User;
import com.yunblog.service.TypeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;


/**
 *
 * @Author: huang
 * @Date: 2021/09/08/0:43
 * @Description: 类型
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    /**
     * 分页显示
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/types")
    public String types(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Type> list = typeService.PageType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pages",pageInfo);
        model.addAttribute("type",list);
        return "admin/types";
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/types/search")
    public String totypes(@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "5")int pageSize, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Type> list = typeService.PageType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pages",pageInfo);
        model.addAttribute("type",list);
        return "admin/types :: typesList";
    }

    /**
     * 新增跳转
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    /**
     * 新增保存
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/saveType")
    public String post(@Valid Type type, RedirectAttributes  attributes){
        Type name = typeService.getByName(type.getName());
        System.out.println(name);
        if (name == null){
           //
        }else {
            attributes.addFlashAttribute("msg","不能存在相同分类");
            return "redirect:/admin/types/input";
        }

        int saveType = typeService.saveType(type);
        if (saveType == 0){
            attributes.addFlashAttribute("message","新增失败");
        } else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @GetMapping("/types/delete/{id}")
    public String del(@PathVariable("id") Long id,RedirectAttributes  attributes){
        attributes.addFlashAttribute("message","删除成功");
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }


    @RequestMapping("/types/{id}/update")
    public String update(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));

        return "admin/type-update";
    }


    /**
     * 编辑更新
     * @param type
     * @param attributes
     * @return
     */
    @PostMapping("/saveType/{id}")
    public String epost(@Valid Type type, RedirectAttributes  attributes){
        Type name = typeService.getByName(type.getName());
        System.out.println(name);
        if (name == null){
            //
        }else {
            attributes.addFlashAttribute("msg","不能存在相同分类");
            return "redirect:/admin/types/input";
        }

        int saveType = typeService.updateType(type);
        if (saveType == 0){
            attributes.addFlashAttribute("message","更新失败");
        } else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }


}
