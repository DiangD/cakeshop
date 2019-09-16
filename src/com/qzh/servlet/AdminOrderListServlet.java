package com.qzh.servlet;

import com.qzh.model.Page;
import com.qzh.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {
    private OrderService orderService=new OrderService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status=0;
        if (request.getParameter("status") != null) {
            status= Integer.parseInt(request.getParameter("status"));
        }
        int pageNo=1;
        if (request.getParameter("pageNo")!=null) {
            pageNo= Integer.parseInt(request.getParameter("pageNo"));
        }
        request.setAttribute("status",status);
        Page page=orderService.getOrderList(status,pageNo);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/admin/order_list.jsp?status=0").forward(request,response);
    }
}
