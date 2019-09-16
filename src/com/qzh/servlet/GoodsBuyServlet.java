package com.qzh.servlet;

import com.qzh.model.Goods;
import com.qzh.model.Order;
import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order;
        if (request.getSession().getAttribute("order") != null) {
            order = (Order) request.getSession().getAttribute("order");
        } else {
            order=new Order();
            request.getSession().setAttribute("order",order);
        }
        int goods_id=Integer.parseInt(request.getParameter("goodsid"));
        Goods goods=goodsService.getGoodsById(goods_id);
        if (goods.getStock() > 0) {
            order.addGoods(goods);
            response.getWriter().append("ok");
        } else {
            response.getWriter().append("fail");
        }
    }
}
