package com.hlh.comprehensiveexercise.web;

import com.hlh.comprehensiveexercise.service.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/deleteBrand")
public class DeleteBrandServlet extends HttpServlet {
    private final BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端传回的id值
        String id = request.getParameter("id");
        //调用服务进行删除
        brandService.delete(Integer.parseInt(id));
        //重定向到index
        response.sendRedirect("/index");
    }
}
