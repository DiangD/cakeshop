package com.qzh.servlet;

import com.qzh.model.User;
import com.qzh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_changePassword")
public class UserChangePasswordServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        User user= (User) request.getSession().getAttribute("user");
        System.out.println(password);
        if (password.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userService.updateUserPassword(user);
            request.setAttribute("successMsg", "修改成功！");
            request.getRequestDispatcher("/user_center.jsp").forward(request,response);
        } else {
            request.setAttribute("failMsg","修改失败，原密码错误！");
            request.getRequestDispatcher("/user_center.jsp").forward(request,response);
    }

    }
}
