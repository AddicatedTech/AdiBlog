/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 13:59:00
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-24 23:15:34
 */
package com.adi.web.admin;

import javax.servlet.http.HttpSession;

import com.adi.po.Blog;
import com.adi.po.User;
import com.adi.service.BlogService;
import com.adi.service.TagService;
import com.adi.service.TypeService;
import com.adi.vo.BlogQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String list(
            @PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            BlogQuery blogQuery, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return LIST;

    }

    @PostMapping("/blogs/search")
    public String search(
            @PageableDefault(size = 5, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
            BlogQuery blogQuery, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        // 返回的是 admin/blogs 页面下的 blogList片段 这是一种局部渲染的方法
        return "admin/blogs :: blogList";

    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType()); // 初始化分类信息
        model.addAttribute("tags", tagService.listTag()); // 初始化标签信息
        model.addAttribute("blog", new Blog()); // 初始化博客信息

        return INPUT;
    }

 

    //  编辑博客
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id ,Model model) {
        model.addAttribute("types", typeService.listType()); // 初始化分类信息
        model.addAttribute("tags", tagService.listTag()); // 初始化标签信息


        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog); // 初始化博客信息
        return INPUT;
    }

    // 博客新增，更新方法
    @PostMapping("/blogs")
    public String post( Blog blog,RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b = blogService.saveBlog(blog);  // blog 只对前端进行简单校验，不进行后端校验了

        if (b == null) {
            // 失败的时候给出提示 操作之后吧消息推送到前端页面，，同样前端页面也需要接收
            // <p th:text="${message}">操作成功！</p>
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            // 成功的时候也给出提示
            attributes.addFlashAttribute("message", "操作成功");
        }

        return REDIRECT_LIST; // 提交成功返回博客列表页面
    }
}
