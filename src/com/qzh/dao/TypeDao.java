package com.qzh.dao;

import com.qzh.model.Type;
import com.qzh.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDao {
    public List<Type> selectAll() throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from type";
        return qr.query(sql, new BeanListHandler<>(Type.class));
    }
    public Type select(int id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select * from type where id=?";
        return qr.query(sql, new BeanHandler<>(Type.class),id);
    }
    public void insert(String name) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="insert into type(name) values (?)";
        qr.update(sql,name);
    }

    public void update(Type type) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="update type set name=? where id=?";
        qr.update(sql,type.getName(),type.getId());
    }

    public void delete(int id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="delete from type where id=?";
        qr.update(sql,id);
    }
}
