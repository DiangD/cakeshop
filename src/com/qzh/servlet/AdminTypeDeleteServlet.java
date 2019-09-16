package com.qzh.servlet;

import com.qzh.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/type_delete")
public class AdminTypeDeleteServlet extends HttpServlet {
    private TypeService typeService=new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = typeService.deleteType(id);
        if (isSuccess) {
            request.setAttribute("successMsg", "删除成功！");
            request.getRequestDispatcher("/admin/type_list").forward(request, response);
        } else {
            request.setAttribute("failMsg", "删除失败！请先删除该类目的商品！");
            request.getRequestDispatcher("/admin/type_list").forward(request, response);
        }
    }
}
