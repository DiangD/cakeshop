package com.qzh.dao;

import com.qzh.model.Goods;
import com.qzh.model.Recommend;
import com.qzh.utils.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsDao {
    public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=? and r.goods_id=g.id and g.type_id=t.id";
        return qr.query(sql, new MapListHandler(), recommendType);
    }

    public Map<String, Object> getScrollGoods() throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "select g.id,g.name,g.cover,g.price from recommend r,goods g where type=1 and r.goods_id=g.id";
        return qr.query(sql, new MapHandler());
    }

    public List<Goods> selectGoods(int typeId, int pageNo, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        if (typeId == 0) {
            String sql = "select * from goods limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Goods.class), (pageNo - 1) * pageSize, pageSize);
        } else {
            String sql = "select * from goods where type_id=? limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Goods.class), typeId, (pageNo - 1) * pageSize, pageSize);
        }
    }

    public int getGoodsCount(int typeId) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        if (typeId == 0) {
            String sql = "select count(*) from goods";
            return qr.query(sql, new ScalarHandler<Long>()).intValue();
        } else {
            String sql = "select count(*) from goods where type_id=?";
            return qr.query(sql, new ScalarHandler<Long>(), typeId).intValue();
        }
    }

    public List<Goods> selectRecommendGoods(int type, int pageNo, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        if (type == 0) {
            String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.id type_id,t.name type_name from goods g,type t where g.type_id=t.id order by g.id limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Goods.class), (pageNo - 1) * pageSize, pageSize);
        } else {
            String sql = " select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.id type_id,t.name type_name from recommend r,goods g,type t where r.type=? and r.goods_id=g.id and g.type_id=t.id order by g.id limit ?,?";
            return qr.query(sql, new BeanListHandler<>(Goods.class), type, (pageNo - 1) * pageSize, pageSize);
        }
    }

    public int getGoodsRecommendCount(int type) throws SQLException {
        if (type==0) {
           return getGoodsCount(0);
        }
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "select count(*) from recommend where type=?";
        return qr.query(sql, new ScalarHandler<Long>(), type).intValue();
    }

    public Goods getGoodsById(int id) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.id type_id,t.name type_name from goods g,type t where g.id=? and g.type_id=t.id";
        return qr.query(sql,new BeanHandler<>(Goods.class),id);
    }

    public int getSearchCount(String keyword) throws SQLException {
        QueryRunner qr=new QueryRunner(DBUtil.getDataSource());
        String sql="select count(*) from goods where name like ?";
        return qr.query(sql,new ScalarHandler<Long>(),"%"+keyword+"%").intValue();
    }

    public List<Goods> selectSearchGoods(String keyword, int pageNo, int pageSize)throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from goods  where name like ? limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Goods.class), "%"+keyword+"%", (pageNo - 1) * pageSize, pageSize);
    }

    public boolean isScroll(Goods goods) throws SQLException {
        return isRecommend(goods,1);
    }

    public boolean isHot(Goods goods) throws SQLException {
        return isRecommend(goods,2);
    }

    public boolean isNew(Goods goods) throws SQLException {
        return isRecommend(goods,3);
    }

    private boolean isRecommend(Goods goods,int type) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "select * from recommend where type=? and goods_id=?";
        Recommend recommend = qr.query(sql, new BeanHandler<>(Recommend.class),type,goods.getId());
        if (recommend == null) {
            return false;
        } else {
            return true;
        }
    }

    public void addRecommend(int id,int type) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into recommend (type,goods_id) values (?,?)";
        qr.update(sql,type,id);
    }

    public void removeRecommend(int id,int type) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql = "delete from recommend where type=? and goods_id=?";
        qr.update(sql,type,id);
    }

    public void insert(Goods goods) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql="insert into goods(name,cover,image1,image2,intro,stock,price,type_id) values (?,?,?,?,?,?,?,?)";
        qr.update(sql,goods.getName(),goods.getCover(),goods.getImage1(),goods.getImage2(),goods.getIntro(),goods.getStock(),goods.getPrice(),goods.getType().getId());
    }

    public void update(Goods goods) throws SQLException {
        QueryRunner qr = new QueryRunner(DBUtil.getDataSource());
        String sql="update goods set name=?,cover=?,image1=?,image2=?,intro=?,stock=?,price=?,type_id=? where id=?";
        qr.update(sql,goods.getName(),goods.getCover(),goods.getImage1(),goods.getImage2(),goods.getIntro(),goods.getStock(),goods.getPrice(),goods.getType().getId(),goods.getId());
    }

    public void delete(Connection conn,int id) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql="delete from goods where id=?";
        qr.update(conn,sql,id);
    }

    public void deleteRecommend(Connection conn,int goods_id) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql="delete from recommend where goods_id=?";
        qr.update(conn,sql,goods_id);
    }
}
