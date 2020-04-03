package com.imooc.filter;

import javax.servlet.*;
import java.io.IOException;

// @Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init ok");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long starter = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end-starter) + "ms");
    }

    @Override
    public void destroy() {
        System.out.println("time filter has destroy");
    }
}
