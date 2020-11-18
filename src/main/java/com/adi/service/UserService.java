/*
 * @Descripttion:  
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 08:40:22
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 08:59:47
 */
package com.adi.service;

import com.adi.po.User;

public interface UserService {

    User checkUser(String username ,String password);
    
}
