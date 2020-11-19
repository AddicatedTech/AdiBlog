/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 09:42:58
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 13:04:45
 */
package com.adi.web.admin;

import javax.validation.Valid;

import com.adi.po.Tag;
import com.adi.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    // get请求 tag列表
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    // post请求 添加tag
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    // get 修改 tag, 根据id进入修改页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    // 新增请求
    @PostMapping("/tags")
    public String post(@Valid Tag tag,BindingResult result,RedirectAttributes attributes){
        Tag t1 = tagService.getTagByName(tag.getName());

        if(t1!=null){
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            // 失败的时候给出提示 操作之后吧消息推送到前端页面，，同样前端页面也需要接收
            // <p th:text="${message}">操作成功！</p>
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            // 成功的时候也给出提示
            attributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/tags";
    }
    

    // 修改tag 请求
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Tag t1 = tagService.getTagByName(tag.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加标签");
        }

        if (result.hasErrors()) {
            return "admin/tags-input";
        }

        Tag t = tagService.getTag(id);
        if (t == null) {
            // 失败的时候给出提示 操作之后吧消息推送到前端页面，，同样前端页面也需要接收
            // <p th:text="${message}">操作成功！</p>
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            // 成功的时候也给出提示
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";


    }
    





    // get请求 删除tag
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";

    }

}
