package com.qzh.servlet;

import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = goodsService.deleteGoods(id);
        if (isSuccess) {
            request.setAttribute("successMsg", "删除成功！");
            request.getRequestDispatcher("/admin/goods_list").forward(request, response);
        } else {
            request.setAttribute("failMsg","存在订单包含此商品，无法删除！");
            request.getRequestDispatcher("/admin/goods_list").forward(request, response);
        }
    }
}
