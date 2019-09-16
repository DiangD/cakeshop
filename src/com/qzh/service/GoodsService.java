package com.qzh.service;

import com.qzh.dao.GoodsDao;
import com.qzh.model.Goods;
import com.qzh.model.Page;
import com.qzh.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsService {
    private GoodsDao goodsDao=new GoodsDao();
    public List<Map<String, Object>> getHotGoodsList(){
        List<Map<String, Object>> list=null;
        try {
            list=goodsDao.getGoodsList(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Map<String, Object>> getNewGoodsList(){
        List<Map<String, Object>> list=null;
        try {
            list=goodsDao.getGoodsList(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Map<String,Object> getScrollGoods(){
        Map<String,Object> map= null;
        try {
            map = goodsDao.getScrollGoods();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    public List<Goods> selectGoods(int typeId, int pageNum, int pageSize) {
        List<Goods> goodsList = null;
        try {
            goodsList = goodsDao.selectGoods(typeId, pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    public Page getGoodsPage(int typeId,int pageNo) {
        Page p=new Page();
        p.setPageNumber(pageNo);
        int totalCount=0;
        try {
            totalCount=goodsDao.getGoodsCount(typeId);
            p.setTotalCount(totalCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setPageSizeAndTotalCount(8,totalCount);
        List goodsList = null;
        try {
            goodsList = goodsDao.selectGoods(typeId, pageNo, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(goodsList);
        return p;
    }

    public Page getGoodsRecommendPage(int type,int pageNo) {
        Page p=new Page();
        p.setPageNumber(pageNo);
        int totalCount=0;
        try {
            totalCount=goodsDao.getGoodsRecommendCount(type);
            p.setTotalCount(totalCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setPageSizeAndTotalCount(8,totalCount);
        List goodsList = null;
        try {
            goodsList = goodsDao.selectRecommendGoods(type,pageNo,8);
            for (Goods goods:(List<Goods>)goodsList) {
                goods.setScroll(goodsDao.isScroll(goods));
                goods.setHot(goodsDao.isHot(goods));
                goods.setNew(goodsDao.isNew(goods));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(goodsList);
        return p;
    }

    public Goods getGoodsById(int id) {
        Goods goods=null;
        try {
            goods=goodsDao.getGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public Page getSearchGoodsPage(String keyword, int pageNo) {
        Page p=new Page();
        p.setPageNumber(pageNo);
        int totalCount=0;
        try {
            totalCount=goodsDao.getSearchCount(keyword);
            p.setTotalCount(totalCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setPageSizeAndTotalCount(8,totalCount);
        List goodsList = null;
        try {
            goodsList = goodsDao.selectSearchGoods(keyword, pageNo, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(goodsList);
        return p;
    }

    public void addRecommend(int id,int type) {
        try {
            goodsDao.addRecommend(id,type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRecommend(int id,int type) {
        try {
            goodsDao.removeRecommend(id,type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGoods(Goods goods) {
        try {
            goodsDao.insert(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGoods(Goods goods) {
        try {
            goodsDao.update(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteGoods(int id) {
        Connection conn= null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            goodsDao.deleteRecommend(conn,id);
            goodsDao.delete(conn,id);
            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
