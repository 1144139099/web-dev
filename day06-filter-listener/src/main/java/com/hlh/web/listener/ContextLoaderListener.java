package com.hlh.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextLoaderListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    private static Map<String, Object> sessionMap;
    public ContextLoaderListener(){

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ContextLoaderListener初始化，加载资源。。。");
        sessionMap = new HashMap<>(8);
        sce.getServletContext().setAttribute("sessionMap", sessionMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("sessionCreated销毁，释放资源。。。");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        System.out.println(session.getAttribute("username") + "上线了。。。");
        sessionMap.put(session.getId(), session.getAttribute("username"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        System.out.println(session.getAttribute("username") + "下线了。。。");
        sessionMap.remove(session.getId(), session.getAttribute("username"));
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated, 创建了新的会话");
    }
}
