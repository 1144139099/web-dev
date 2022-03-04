package com.hlh.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CharacterFilter",urlPatterns = "/*")
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterFilter初始化");
    }

    @Override
    public void destroy() {
        System.out.println("CharacterFilter销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);

    }
}
