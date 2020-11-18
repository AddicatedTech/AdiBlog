/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 08:41:38
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 13:32:34
 */
package com.adi.service;

import com.adi.dao.UserRepository;
import com.adi.po.User;
import com.adi.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  // 告诉spring这是一个service，交由spring内部处理，进行ioc
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        // 在这里调用由jpa实现的持久层代码
        User user =  userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
