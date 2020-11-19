/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 19:48:27
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-19 08:21:43
 */
package com.adi.dao;
import com.adi.po.Type;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Long> {
    //  pojoRepository 必须是一个接口，然后去继承JpaRepository

    Type findByName(String name);
}
