/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 14:03:23
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 14:25:13
 */
package com.adi.dao;

import com.adi.po.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogRepository extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog> {
    
}
