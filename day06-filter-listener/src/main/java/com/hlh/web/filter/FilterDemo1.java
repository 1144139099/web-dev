package com.hlh.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
public class FilterDemo1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器被执行");
        System.out.println("1111111");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("2222222");

    }
    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
