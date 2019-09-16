package com.qzh.servlet;

import com.qzh.model.User;
import com.qzh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user_reset")
public class AdminUserResetServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        String password=request.getParameter("password");
        User user=userService.getUserById(id);
        user.setPassword(password);
        userService.updateUserPassword(user);
        request.setAttribute("successMsg","修改成功！");
        request.getRequestDispatcher("/admin/user_list").forward(request,response);
    }
}
