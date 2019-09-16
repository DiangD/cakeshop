package com.qzh.servlet;

import com.qzh.model.Page;
import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int pageNo=1;
        if (request.getParameter("pageNo")!=null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo"));
        }
        Page page=goodsService.getSearchGoodsPage(keyword,pageNo);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/goods_search.jsp").forward(request,response);
    }
}
