package com.qzh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order_submit")
public class OrderSubmitServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
        } else {
            request.setAttribute("failMsg","请登录后，提交订单！");
            request.setAttribute("skip","skip");
            request.getRequestDispatcher("/user_login.jsp").forward(request,response);
        }
    }
}
