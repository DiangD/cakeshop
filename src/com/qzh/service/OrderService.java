package com.qzh.service;

import com.qzh.dao.OrderDao;
import com.qzh.model.Order;
import com.qzh.model.OrderItem;
import com.qzh.model.Page;
import com.qzh.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {
    OrderDao orderDao=new OrderDao();
    public void addOrder(Order order) {
        Connection conn=null;
        try {
            conn= DBUtil.getConnection();
            conn.setAutoCommit(false);
            orderDao.insertOrder(conn,order);
            int id = orderDao.getLastInsertId(conn);
            order.setId(id);
            for (OrderItem orderItem:order.getItemMap().values()) {
                orderDao.insertOrderItem(conn,orderItem);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn!=null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public List<Order> selectAllOrder(int user_id) {
        List<Order> orderList= null;
        try {
            orderList = orderDao.selectAllOrder(user_id);
            for (Order order:orderList) {
                order.setItemList(selectAllItem(order.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<OrderItem> selectAllItem(int order_id) {
        List<OrderItem> orderItems = null;
        try {
            orderItems = orderDao.selectAllItem(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    public Page getOrderList(int status, int pageNo) {
        Page p=new Page();
        p.setPageNumber(pageNo);
        int totalCount=0;
        int pageSize=10;
        try {
            totalCount=orderDao.getOrderCount(status);
            p.setTotalCount(totalCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setPageSizeAndTotalCount(pageSize,totalCount);
        List orderList = null;
        try {
            orderList = orderDao.selectOrderList(status, pageNo, pageSize);
            for (Order order:(List<Order>)orderList) {
                List<OrderItem> itemList=orderDao.selectAllItem(order.getId());
                order.setItemList(itemList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(orderList);
        return p;
    }

    public void updateOrderStatus(int id,int status){
        try {
            orderDao.updateOrderStatus(id,status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        Connection conn=null;
        try {
            conn=DBUtil.getConnection();
            conn.setAutoCommit(false);
            orderDao.deleteOrderItem(conn,id);
            orderDao.deleteOrder(conn,id);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn!=null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
