package com.hlh.comprehensiveexercise.web;

import com.hlh.comprehensiveexercise.entity.Brand;
import com.hlh.comprehensiveexercise.service.BrandService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @description: 首页，查询所有品牌
 * @author: hlh
 * @date: 2022-02-28
 **/
@WebServlet(urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用BrandService完成查询
        List<Brand> brands = brandService.selectAll();
        //2. 存入request域中
        request.setAttribute("brands", brands);
        //3. 转发到brand.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}