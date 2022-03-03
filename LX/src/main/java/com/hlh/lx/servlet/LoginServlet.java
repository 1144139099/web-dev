package com.hlh.lx.servlet;

import com.alibaba.fastjson.JSON;
import com.hlh.lx.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String account=req.getParameter("account");
        String password=req.getParameter("password");
        System.out.println(account + password);
        User user = new User(account, password);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        out.print(JSON.toJSONString(user));
        out.flush();
        out.close();
    }
}
