package com.qzh.servlet;

import com.qzh.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods_lessen")
public class GoodsLessenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order= (Order) request.getSession().getAttribute("order");
        int goods_id=Integer.parseInt(request.getParameter("goodsid"));
        order.lessenGoods(goods_id);
        response.getWriter().append("ok");
    }
}
