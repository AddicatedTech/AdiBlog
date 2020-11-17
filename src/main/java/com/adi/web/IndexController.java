/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-16 17:57:25
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-17 12:36:11
 */
package com.adi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/") // 在 url中接收参数的情况下使用pathVaribale注解来接受url中的变量
    public String index() {

        // int i = 9 / 0;
        // String blog = null;
        // if(blog == null){
        // throw new NotFoundException("博客找不到了~");
        // }
        System.out.println("----------index------------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        System.out.println("----------blog------------");
        return "blog";
    }
}
