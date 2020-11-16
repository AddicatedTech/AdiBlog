/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-16 17:57:25
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-16 18:57:22
 */
package com.adi.web;

import com.adi.NotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // int i = 9 / 0;
        String blog = null;
        if(blog == null){
            throw new NotFoundException("博客找不到了~"); 
        }
        return "index";
    }
}
