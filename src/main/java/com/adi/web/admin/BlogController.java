/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 13:59:00
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 20:52:19
 */
package com.adi.web.admin;

import com.adi.service.BlogService;
import com.adi.service.TypeService;
import com.adi.vo.BlogQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    
    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,BlogQuery blogQuery,Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        return "admin/blogs";

    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,BlogQuery blogQuery,Model model){
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        //  返回的是 admin/blogs 页面下的 blogList片段  这是一种局部渲染的方法
        return "admin/blogs :: blogList";

    }
}
