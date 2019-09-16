package com.qzh.service;

import com.qzh.dao.OrderDao;
import com.qzh.dao.UserDao;
import com.qzh.model.Page;
import com.qzh.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao=new UserDao();
    public boolean register(User user) {
        try {
            if (userDao.isUsernameExist(user.getUsername())) {
                return false;
            }
            if (userDao.isEmailExist(user.getEmail())) {
                return false;
            }
            userDao.addUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User login(String ue,String password) {
        User user= null;
        try {
            user = userDao.selectByUsernamePassword(ue,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        }
        try {
            user=userDao.selectByEmailPassword(ue,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUserAddress(User user) {
        try {
            userDao.updateUserAddress(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPassword(User user) {
        try {
            userDao.updateUserPassword(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Page getUserList(int pageNo) {
        Page p=new Page();
        p.setPageNumber(pageNo);
        int totalCount=0;
        int pageSize=7;
        try {
            totalCount=userDao.getUserCount();
            p.setTotalCount(totalCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setPageSizeAndTotalCount(pageSize,totalCount);
        List userList=null;
        try {
            userList=userDao.selectUserList(pageNo,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(userList);
        return p;
    }

    public User getUserById(int id) {
        User user=null;
        try {
            user=userDao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean deleteUser(int id) {
        try {
            userDao.deleteUser(id);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
