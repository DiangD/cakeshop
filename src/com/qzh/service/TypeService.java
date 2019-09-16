package com.qzh.service;

import com.qzh.dao.TypeDao;
import com.qzh.model.Type;

import java.sql.SQLException;
import java.util.List;

public class TypeService {
    private TypeDao typeDao=new TypeDao();
    public List<Type> selectAll(){
        List<Type> list = null;
        try {
            list = typeDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Type select(int id) {
        Type type= null;
        try {
            type = typeDao.select(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public void insertType(String name) {
        try {
            typeDao.insert(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateType(Type type) {
        try {
            typeDao.update(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteType(int id) {
        try {
            typeDao.delete(id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
