package com.qzh.servlet;

import com.qzh.model.Type;
import com.qzh.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {
    private TypeService typeService=new TypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> typeList = typeService.selectAll();
        request.setAttribute("list",typeList);
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request,response);
    }
}
