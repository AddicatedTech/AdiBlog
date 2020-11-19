/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 20:15:44
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 09:14:42
 */
package com.adi.web.admin;

import javax.validation.Valid;

import com.adi.po.Type;
import com.adi.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable,
            Model model) {
        // 使用model 将分页查询出来的数据返回到前端
        // TODO 整理
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";

    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type t1 = typeService.getTypeByName(type.getName());

        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.saveType(type);
        if (t == null) {
            // 失败的时候给出提示 操作之后吧消息推送到前端页面，，同样前端页面也需要接收
            // <p th:text="${message}">操作成功！</p>
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            // 成功的时候也给出提示
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String ditPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
                            //  bindingResult 一定是要与被验证类的参数是挨着的
        Type t1 = typeService.getTypeByName(type.getName());

        if (t1 != null) {
            result.rejectValue("name", "nameError", "不能重复添加分类");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type t = typeService.updaType(id,type);
        if (t == null) {
            // 失败的时候给出提示 操作之后吧消息推送到前端页面，，同样前端页面也需要接收
            // <p th:text="${message}">操作成功！</p>
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            // 成功的时候也给出提示
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
    // 删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";

    }
}