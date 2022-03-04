package com.hlh.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String[] urls = {"/login.jsp","/loginServlet"};
        String url = request.getRequestURI().toString();
        for(String u : urls){
            if(url.contains(u)){
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }



        //取出Session和user
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String msg = "";
        if (user != null) {
            //登录过了，放行
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //没有登录，直接跳到登录页面
             msg = "没有登录";
            request.setAttribute("msg", "没有登录");
            request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        }

    }
    @Override
    public void destroy() {
        System.out.println("过滤器2销毁");
    }
}
