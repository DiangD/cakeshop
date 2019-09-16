package com.qzh.dao;

import com.qzh.model.User;
import com.qzh.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void addUser(User user) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="insert into user (username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
        qr.update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getName(),user.getPhone(),user.getAddress(),user.isIsadmin(),user.isIsvalidate());

    }
    public boolean isUsernameExist(String username) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user where username=?";
        User user = qr.query(sql, new BeanHandler<>(User.class), username);
        return user != null;
    }

    public boolean isEmailExist(String email) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user where email=?";
        User user = qr.query(sql, new BeanHandler<>(User.class),email);
        return user != null;
    }

    public User selectByUsernamePassword(String username,String password) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user where username=? and password=?";
        return qr.query(sql, new BeanHandler<>(User.class),username,password);
    }

    public User selectByEmailPassword(String email,String password) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user where email=? and password=?";
        return qr.query(sql, new BeanHandler<>(User.class),email,password);
    }

    public void updateUserAddress(User user) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="update user set name=?,phone=?,address=? where id=?";
        qr.update(sql,user.getName(),user.getPhone(),user.getAddress(),user.getId());
    }

    public void updateUserPassword(User user) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="update user set password=? where id=?";
        qr.update(sql,user.getPassword(),user.getId());
    }

    public int getUserCount() throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select count(*) from user";
        return qr.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public List selectUserList(int pageNo, int pageSize) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user limit ?,?";
        return  qr.query(sql,new BeanListHandler<>(User.class),(pageNo-1)*pageSize,pageSize);
    }

    public User getUserById(int id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from user where id=?";
        return qr.query(sql,new BeanHandler<>(User.class),id);
    }

    public void deleteUser(int id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="delete from user where id=?";
        qr.update(sql,id);
    }
}
