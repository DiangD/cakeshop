package com.qzh.servlet;

import com.qzh.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得条幅
        Map<String, Object> scrollGoods = goodsService.getScrollGoods();
        request.setAttribute("scroll",scrollGoods);
        // 取得热销数据
        List<Map<String, Object>> hotGoodsList=goodsService.getHotGoodsList();
        request.setAttribute("hotList",hotGoodsList);
        //取得新品
        List<Map<String, Object>> newGoodsList=goodsService.getNewGoodsList();
        request.setAttribute("newList",newGoodsList);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
