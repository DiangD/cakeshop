package com.qzh.servlet;

import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget= Integer.parseInt(request.getParameter("typeTarget"));
        if (method.equals("add")) {
            goodsService.addRecommend(id,typeTarget);
        } else if (method.equals("remove")) {
            goodsService.removeRecommend(id,typeTarget);
        }
        request.getRequestDispatcher("/admin/goods_list").forward(request,response);
    }
}
