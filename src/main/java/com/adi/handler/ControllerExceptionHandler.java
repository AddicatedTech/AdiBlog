/*
 * @Descripttion: 
 * @version: 全局异常控制
 * @Author: Addicated
 * @Date: 2020-11-16 18:07:31
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-16 19:04:24
 */
package com.adi.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 拦截所有controller 如果发生异常
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class) // 标识该方法是可以做异常拦截的 参数为拦截的类对象
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL : {},Exception : {}", request.getRequestURL(), e);

        // 判断一下吧 HttpStatus的异常放过不进行拦截
        // 指定如果httpStatus返回不为空，就放行不进行异常拦截，抛出异常让Springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }

}
