package com.qzh.servlet;

import com.qzh.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/type_add")
public class AdminTypeAddServlet extends HttpServlet {
    private TypeService typeService=new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        typeService.insertType(name);
        request.setAttribute("successMsg","添加成功！");
        request.getRequestDispatcher("/admin/type_list").forward(request,response);
    }
}
