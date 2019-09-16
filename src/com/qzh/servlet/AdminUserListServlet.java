package com.qzh.servlet;

import com.qzh.model.Page;
import com.qzh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user_list")
public class AdminUserListServlet extends HttpServlet {
    private UserService userService=new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo=1;
        if (request.getParameter("pageNo")!=null) {
            pageNo= Integer.parseInt(request.getParameter("pageNo"));
        }
        Page page=userService.getUserList(pageNo);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/admin/user_list.jsp").forward(request,response);
    }
}
