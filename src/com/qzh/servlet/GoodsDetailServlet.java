package com.qzh.servlet;

import com.qzh.model.Goods;
import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Goods goods=goodsService.getGoodsById(id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/goods_detail.jsp").forward(request,response);
    }
}
