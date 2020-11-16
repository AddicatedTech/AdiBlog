/*
 * @Descripttion: 日志处理切面类
 * @version:  
 * @Author: Addicated
 * @Date: 2020-11-16 19:35:32
 * @LastEditors: Addicated
 * @LastEditTime: 2020-11-16 20:54:48
 */
package com.adi.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect // aop关键注解，切面编程
@Component // 开启组件扫描
public class LogAspect {
    // 前端 - 请求 - 后台服务 - 某个类，某个方法 整个是一个流的感觉
    // 切面就是在这个流之中 切上一刀，在切口上加入日志处理方法，记录下来流程中的信息

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.adi.web.*.*(..))") // 声明是一个切面方法，execution(声明拦截的类)
    public void log() {

    }

    @Before("log()") // 参数为传递切面方法
    public void doBefore(JoinPoint joinPoint) { // 获取到classmethod需要使用到 joinPoint对象来获取
        // 切面之前执行的
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        
        logger.info("Request : {}", requestLog);
    }

    @After("log()") // 参数为传递切面方法
    public void doAfter() {
        // 切面之后执行的
        logger.info("----------------------------doAfter------------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()") // 要给一个参数就是下面方法的对象，还有一个切面方法的参数
    public void doAfterRuturn(Object result) {
        logger.info("最后执行");
        logger.info("Result : {}", result);
    }

    // 定义日志中要获取的信息实体类
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" + "url='" + url + '\'' + ", ip='" + ip + '\'' + ", classMethod='" + classMethod + '\''
                    + ", args=" + Arrays.toString(args) + '}';
        }

    }
}
