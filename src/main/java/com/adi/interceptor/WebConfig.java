/*
 * @Descripttion: 拦截器配置类
 * @version: 
 * @Author: Addicated
 * @Date: 2020-11-18 15:34:30
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-18 15:40:46
 */
package com.adi.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration  // 告诉spring 这是一个配置类
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //  在这里吧之前的拦截配置一下，使其生效
        //  之后调用addPath方法对参数下的所有路径进行拦截
        registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/admin/**")
        .excludePathPatterns("/admin")
        .excludePathPatterns("/admin/login");

    }
}
