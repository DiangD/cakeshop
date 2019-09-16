package com.qzh.model;

import com.qzh.utils.PriceUtil;

import java.util.*;

public class Order {
    private int id;
    private float total;
    private int amount;
    private int status;
    private int paytype;
    private String name;
    private String phone;
    private String address;
    private Date datetime;
    private User user;
    private Map<Integer,OrderItem> itemMap = new HashMap<>();
    private List<OrderItem> itemList=new ArrayList<>();

    public void addGoods(Goods goods) {
        if (itemMap.containsKey(goods.getId())) {
            OrderItem orderItem=itemMap.get(goods.getId());
            orderItem.setAmount(orderItem.getAmount()+1);
        } else {
            OrderItem orderItem=new OrderItem(goods.getPrice(),1,goods,this);
            itemMap.put(goods.getId(),orderItem);
        }
        amount++;
        total= PriceUtil.add(total,goods.getPrice());
    }

    public void lessenGoods(int goods_id) {
        if (itemMap.containsKey(goods_id)) {
            OrderItem orderItem=itemMap.get(goods_id);
            orderItem.setAmount(orderItem.getAmount()-1);
            amount--;
            total= PriceUtil.subtract(total,orderItem.getPrice());
            if (orderItem.getAmount()<=0) {
                itemMap.remove(goods_id);
            }
        }
    }

    public  void deleteGoods(int goods_id) {
        if (itemMap.containsKey(goods_id)) {
            OrderItem orderItem=itemMap.get(goods_id);
            amount=amount-orderItem.getAmount();
            total= PriceUtil.subtract(total,orderItem.getPrice()*orderItem.getAmount());
            itemMap.remove(goods_id);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Integer, OrderItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
    public void setUsername(String username) {
        if (user == null) {
            user=new User();
        }
        user.setUsername(username);
    }

    public Order() {
    }
}
