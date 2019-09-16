package com.qzh.servlet;

import com.qzh.model.Page;
import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type=0;
        if (request.getParameter("type")!=null) {
            type= Integer.parseInt(request.getParameter("type"));
        }
        int pageNo=1;
        if (request.getParameter("pageNo")!=null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo"));
        }
        Page page=goodsService.getGoodsRecommendPage(type,pageNo);
        request.setAttribute("type",type);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
