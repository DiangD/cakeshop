package com.qzh.servlet;

import com.qzh.model.Goods;
import com.qzh.service.GoodsService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {
    private GoodsService goodsService=new GoodsService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo=1;
        int type=0;
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        try {
            List<FileItem> itemList = upload.parseRequest(request);
            Goods goods=new Goods();
            for (FileItem item:itemList) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "id":
                            goods.setId(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "name":
                            goods.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            goods.setPrice(Float.parseFloat(item.getString("utf-8")));
                            break;
                        case "intro":
                            goods.setIntro(item.getString("utf-8"));
                            break;
                        case "cover":
                            goods.setCover(item.getString("utf-8"));
                            break;
                        case "image1":
                            goods.setImage1(item.getString("utf-8"));
                            break;
                        case "image2":
                            goods.setImage2(item.getString("utf-8"));
                            break;
                        case "stock":
                            goods.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "type_id":
                            goods.setType_id(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "pageNo":
                            pageNo=(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "type":
                            type=(Integer.parseInt(item.getString("utf-8")));
                            break;
                    }
                } else {
                    if (item.getInputStream().available() <= 0) continue;
                        String fileName=item.getName();
                        fileName=fileName.substring(fileName.lastIndexOf("."));
                        fileName="/"+new Date().getTime()+fileName;
                        String path=this.getServletContext().getRealPath("/picture")+fileName;
                        System.out.println(path);
                        InputStream in=item.getInputStream();
                        FileOutputStream out = new FileOutputStream(path);
                        byte[] buffer=new byte[1024];
                        int len;
                        while ((len=in.read(buffer))>0) {
                            out.write(buffer);
                        }
                        in.close();
                        out.close();
                        item.delete();
                        switch (item.getFieldName()) {
                            case "cover" :
                                goods.setCover("/picture"+fileName);
                                break;
                            case "image1" :
                                goods.setImage1("/picture"+fileName);
                                break;
                            case "image2" :
                                goods.setImage2("/picture"+fileName);
                                break;
                        }
                    }
                }
            goodsService.updateGoods(goods);
            request.getRequestDispatcher("/admin/goods_list?pageNo="+pageNo+"&type="+type).forward(request,response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
