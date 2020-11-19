/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 19:41:28
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 08:20:21
 */
package com.adi.service;

import com.adi.po.Type;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    // 参数应该是一个可以进行分页的对象
    // Springboot使用分页查询要使用page进行声明实体类型
    Page<Type> listType(Pageable pageable);

    Type getTypeByName(String name);

    Type updaType(Long id, Type type);

    void deleteType(Long id);
}
