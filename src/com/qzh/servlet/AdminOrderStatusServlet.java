package com.qzh.servlet;

import com.qzh.model.Order;
import com.qzh.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {
    private OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        orderService.updateOrderStatus(id,status);
        response.sendRedirect(request.getContextPath()+"/admin/order_list?status="+status);
    }
}
