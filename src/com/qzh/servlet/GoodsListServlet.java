package com.qzh.servlet;

import com.qzh.model.Page;
import com.qzh.model.Type;
import com.qzh.service.GoodsService;
import com.qzh.service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    private TypeService typeService=new TypeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        if(request.getParameter("id")!=null){
            id=Integer.parseInt(request.getParameter("id"));
        }
        int pageNo=1;
        if (request.getParameter("pageNo")!=null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo"));
        }
        Type type=null;
        if (id!=0) {
            type=typeService.select(id);
        }
        Page p=goodsService.getGoodsPage(id,pageNo);
        request.setAttribute("type",type);
        request.setAttribute("page",p);
        request.setAttribute("id",id);
        request.getRequestDispatcher("/goodslist.jsp").forward(request,response);
    }
}
