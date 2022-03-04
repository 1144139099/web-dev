package com.hlh.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class SensitiveWordFilter implements Filter {
    private final List<String> list = new ArrayList<>();
    private final String methodName = "getParameter";
    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        ServletRequest proxyReq = (ServletRequest)
                Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(methodName.equals(method.getName())){
                            String value = (String) method.invoke(req, args);
                            if(value != null){
                                for(String str : list){
                                    if(value.contains(str)){
                                        value = value.replaceAll(str, "***");
                                    }
                                }
                            }
                            return value;
                        }
                        return method.invoke(req, args);
                    }
                });
        chain.doFilter(proxyReq, resp);
    }
    @Override
    public void destroy() {
        System.out.println("敏感词过滤器被销毁");
    }

}
