/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 13:59:50
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 20:13:22
 */
package com.adi.service;

import com.adi.po.Blog;
import com.adi.vo.BlogQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    
    //  后台管理crud blog
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable,BlogQuery blogQuery);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id ,Blog blog);

    void deleteBlog(Long id);
    
}
