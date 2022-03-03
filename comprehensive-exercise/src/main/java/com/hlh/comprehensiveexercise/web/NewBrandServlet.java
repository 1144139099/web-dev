package com.hlh.comprehensiveexercise.web;

import com.alibaba.fastjson.JSON;
import com.hlh.comprehensiveexercise.entity.Brand;
import com.hlh.comprehensiveexercise.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/newBrand")
public class NewBrandServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 如果用 request.getParameter 是错的，不能接收json的数据
        // String brandName = request.getParameter("brandName");
        // System.out.println(brandName);

        // 1. 用缓冲字符输入流来获取请求体数
        StringBuilder json = new StringBuilder();
        String line;
        BufferedReader br =req.getReader();
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        //2. 将JSON字符串转为Java对象
        Brand brand= JSON.parseObject(json.toString(), Brand.class);
        //3. 调用service 添加
        brandService.add(brand);//
        // 4. 响应成功
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
