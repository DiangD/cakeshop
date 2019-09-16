package com.qzh.servlet;

import com.qzh.model.User;
import com.qzh.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/user_register")
public class UserRegisterServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (userService.register(user)) {
            request.setAttribute("successMsg","注册成功，请登录！");
            request.getRequestDispatcher("/user_login.jsp").forward(request,response);
        } else {
            request.setAttribute("msg","用户名或邮箱重复，请重新填写！");
            request.getRequestDispatcher("/user_register.jsp").forward(request,response);
        }
    }


}
