package com.qzh.servlet;

import com.qzh.model.User;
import com.qzh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_login")
public class UserLoginServlet extends HttpServlet {
    private UserService userService=new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");
        User user = userService.login(ue, password);
        if (user == null) {
            request.setAttribute("failMsg", "用户名、邮箱或密码错误，请重新登录！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user",user);
            if (request.getParameter("skip") != null) {
                request.getRequestDispatcher("/order_submit.jsp").forward(request, response);
            } else {
                request.setAttribute("successMsg","登录成功！");
                request.getRequestDispatcher("/user_center.jsp").forward(request,response);
            }
        }
    }
}
