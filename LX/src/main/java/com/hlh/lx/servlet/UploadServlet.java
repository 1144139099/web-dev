package com.hlh.lx.servlet;



import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(urlPatterns = "/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Part part = req.getPart("file");
        // 获取文件上传的字节流
        InputStream inputStream = part.getInputStream();
        // 给文件改名
        String fileName = UUID.randomUUID() +part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        // 路径转换
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("upload/" + fileName);
        part.write(realPath);
        resp.setContentType("text/html;charset:utf-8");
        PrintWriter out = resp.getWriter();
        out.println("文件上传成功");
        out.flush();
        out.close();
    }
    }

