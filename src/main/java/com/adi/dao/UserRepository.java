/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 08:43:48
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 08:43:54
 */
package com.adi.dao;

import com.adi.po.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 使用jpa的情况下，定义一个dao接口并集成JpaRepository<实体类型,主键的类型>
    // 这里面继承过来的方法包括针对实体类的增删改查
    // jpa 进行自定义方法是有专门的命名规则，后期待整理
    User findByUsernameAndPassword(String username, String password);

}
