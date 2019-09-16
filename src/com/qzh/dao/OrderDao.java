package com.qzh.dao;

import com.qzh.model.Order;
import com.qzh.model.OrderItem;
import com.qzh.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public void insertOrder(Connection conn,Order order) throws SQLException {
        QueryRunner qr=new QueryRunner();
        String sql="insert into `order` (total,amount,status,paytype,name,phone,address,datetime,user_id) values(?,?,?,?,?,?,?,?,?)";
        qr.update(conn,sql,order.getTotal(),order.getAmount(),order.getStatus(),order.getPaytype(),order.getName(),order.getPhone(),order.getAddress(),order.getDatetime(),order.getUser().getId());
    }

    public int getLastInsertId(Connection conn) throws SQLException {
        QueryRunner qr=new QueryRunner();
        String sql="select last_insert_id()";
        BigInteger integer= qr.query(conn,sql,new ScalarHandler<>());
        return integer.intValue();
    }

    public void insertOrderItem(Connection conn, OrderItem orderItem) throws SQLException {
        QueryRunner qr=new QueryRunner();
        String sql="insert into orderitem (price,amount,goods_id,order_id) values(?,?,?,?)";
        qr.update(conn,sql,orderItem.getPrice(),orderItem.getAmount(),orderItem.getGoods().getId(),orderItem.getOrder().getId());
    }

    public List<Order> selectAllOrder(int user_id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from `order` where user_id=? order by datetime desc";
        return qr.query(sql,new BeanListHandler<>(Order.class),user_id);
    }

    public List<OrderItem> selectAllItem(int order_id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select i.id,i.price,i.amount ,g.name from orderitem i, goods g where i.order_id=? and i.goods_id=g.id";
        return qr.query(sql,new BeanListHandler<>(OrderItem.class),order_id );
    }

    public int getOrderCount(int status) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        if (status == 0) {
            String sql = "select count(*) from `order` ";
            return qr.query(sql, new ScalarHandler<Long>()).intValue();
        } else {
            String sql="select count(*) from `order` where status=?";
            return qr.query(sql, new ScalarHandler<Long>(),status).intValue();
        }
    }

    public List selectOrderList(int status, int pageNo, int pageSize) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        if (status == 0) {
            String sql = "SELECT o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username FROM `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Order.class),(pageNo-1)*pageSize,pageSize);
        } else {
            String sql="SELECT o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username FROM `order` o,user u where o.user_id=u.id and o.status=? order by o.datetime desc limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Order.class), status,(pageNo-1)*pageSize,pageSize);
        }
    }

    public void updateOrderStatus(int id,int status) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="update `order` set status=? where id=?";
        qr.update(sql,status,id);
    }

    public void deleteOrder(Connection conn,int id) throws SQLException {
        QueryRunner qr=new QueryRunner();
        String sql="delete from `order` where id=?";
        qr.update(conn,sql,id);
    }

    public void deleteOrderItem(Connection conn,int order_id) throws SQLException {
        QueryRunner qr=new QueryRunner();
        String sql="delete from orderitem where order_id=?";
        qr.update(conn,sql,order_id);
    }
}
