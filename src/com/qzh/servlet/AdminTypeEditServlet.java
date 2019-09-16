package com.qzh.servlet;

import com.qzh.model.Type;
import com.qzh.service.TypeService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/admin/type_edit")
public class AdminTypeEditServlet extends HttpServlet {
    private TypeService typeService=new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Type type=new Type();
        try {
            BeanUtils.copyProperties(type,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        typeService.updateType(type);
        request.setAttribute("successMsg","修改成功！");
        request.getRequestDispatcher("/admin/type_list").forward(request,response);
    }
}
