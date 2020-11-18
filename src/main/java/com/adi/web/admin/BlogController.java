/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 13:59:00
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 14:01:26
 */
package com.adi.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {
    
    @GetMapping("/blogs")
    public String list(){
        return "admin/blogs";

    }
}
