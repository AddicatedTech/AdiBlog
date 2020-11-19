/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-19 09:46:14
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 09:49:59
 */
package com.adi.dao;

import com.adi.po.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    // 根据name查找数据
    Tag findByName(String name);

}
