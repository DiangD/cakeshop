package com.qzh.servlet;

import com.qzh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user_delete")
public class AdminUserDeleteServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        boolean isSuccess = userService.deleteUser(id);
        if (isSuccess) {
            request.setAttribute("successMsg", "客户删除成功！");
        } else {
            request.setAttribute("failMsg","该客户有订单未删除，请删除订单!");
        }
        request.getRequestDispatcher("/admin/user_list").forward(request,response);
    }
}
