package com.hlh.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class FilterDemo2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("444444");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("555555");

    }
    @Override
    public void destroy() {
        System.out.println("过滤器2销毁");
    }
}
