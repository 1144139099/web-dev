package com.hlh.lx.servlet;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login1")
public class LoginServlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过缓冲字符流按行读取JSON数据，拼接到可变⻓字符串
        StringBuilder json = new StringBuilder();
        String line;
        BufferedReader br = req.getReader();
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        //将字符串转换成JSON对象返回
        out.print(JSONObject.parseObject(json.toString()));
        out.flush();
        out.close();

    }
}
