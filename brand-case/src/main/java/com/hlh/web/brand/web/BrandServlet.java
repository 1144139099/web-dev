package com.hlh.web.brand.web;

import com.alibaba.fastjson.JSON;
import com.hlh.web.brand.pojo.Brand;
import com.hlh.web.brand.pojo.PageBean;
import com.hlh.web.brand.service.BrandService;
import com.hlh.web.brand.service.impl.BrandServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private final BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
//l.调用service查询
        List<Brand> brands = brandService.selectAll();
        //2.转为JS0N
        String jsonString = JSON.toJSONString(brands);
        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过缓冲字符流按行读取前端传递的查询条件对象
        StringBuilder json = new StringBuilder();
        String line;
        BufferedReader br = request.getReader();
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

//转为Brand对象
        Brand brand = JSON.parseObject(json.toString(), Brand.class);
        //调用service添加
        brandService.add(brand);
//响应成功
        response.getWriter().write("success");

    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
//1．接收当前页码和每页展示条数url?currentPage=1&pageSize=5
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
//2．将字符串参数转化为整型
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
//3．调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
//4．转为JSON
        String jsonString = JSON.toJSONString(pageBean);
//5.向客户端返回数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
//接收当前页码和每页展示条数url?currentPage=1&pageSize=5
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //字符串参数转为整型
        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        //通过缓冲字符流按行读取前端传递的查询条件对象
        StringBuilder json = new StringBuilder();
        String line;
        BufferedReader br = request.getReader();
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

        //转为 Brand对象
        Brand brand = JSON.parseObject(json.toString(), Brand.class);
        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //向客户端返回数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
