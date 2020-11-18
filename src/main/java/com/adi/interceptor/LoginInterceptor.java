/*
 * @Descripttion: 
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 14:23:59
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 15:33:25
 */
package com.adi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    // 做登录的全局拦截 没有登录不允许进入后台博客管理页
    // 继承HandlerInterceptorAdaper ，重写preHandle方法

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 如果session中不存在user ，那就拦截
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false; // 不在向下执行
        }

        return true; // 如果session中含有user信息，就可以接着向下访问
        //  方法编写完成，但是没有指明需要拦截那些路径，所以需要一个配置类来告诉拦截器
    }

}
