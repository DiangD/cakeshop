package com.qzh.servlet;

import com.qzh.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {
    private OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.deleteOrder(id);
        request.getRequestDispatcher("/admin/order_list").forward(request,response);
    }
}
