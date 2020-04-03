package com.imooc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("timeIntercepter preHandler");
        long starter = System.currentTimeMillis();
        httpServletRequest.setAttribute("starterTime", starter);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("timeIntercepter postHandle");
        long end = System.currentTimeMillis();
        long starterTime = (long) httpServletRequest.getAttribute("starterTime");
        System.out.println("耗时:" + (end - starterTime) + "ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("timeIntercepter afterCompletion");
        long end = System.currentTimeMillis();
        long starterTime = (long) httpServletRequest.getAttribute("starterTime");
        System.out.println("耗时:" + (end - starterTime) + "ms");
    }
}
