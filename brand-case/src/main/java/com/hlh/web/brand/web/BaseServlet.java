package com.hlh.web.brand.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1,获取请求路径
// brand-case/ brand/selectAll
        String uri = req.getRequestURI( );
        //System.out.println(uri);
//2．获取最后一段路径，方法名
    int index = uri.lastIndexOf( '/');
    //selectAll? selectAll?
            String methodName = uri.substring( index + 1);
            //System.out.println ( methodName) ;
//2．执行方法
//2.1获取BrandServlet /UserServlet字节码对象Class
// System.out.println(this);
    Class<? extends BaseServlet> cls = this.getClass( );
    //2.2获取方法Method对象
try {
        Method method = cls.getMethod( methodName,HttpServletRequest.class,
                HttpServletResponse.class);
//2.3执行方法
        method.invoke(this, req, resp);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
    e.printStackTrace();
}
    }
}
