package com.qzh.servlet;

import com.qzh.model.Order;
import com.qzh.model.User;
import com.qzh.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
    OrderService orderService=new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = (Order) request.getSession().getAttribute("order");
        try {
            BeanUtils.copyProperties(order,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        order.setPaytype(Integer.parseInt(request.getParameter("paytype")));
        order.setDatetime(new Date());
        order.setStatus(2);
        order.setUser((User) request.getSession().getAttribute("user"));
        orderService.addOrder(order);
        request.getSession().removeAttribute("order");
        request.setAttribute("successMsg","订单支付成功");
        request.getRequestDispatcher("/order_success.jsp").forward(request,response);
    }

}
