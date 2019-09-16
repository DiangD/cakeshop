package com.qzh.model;

public class Goods {
    private int id;
    private String name;
    private String cover;
    private String image1;
    private String image2;
    private String intro;
    private int stock;
    private float price;
    private Type type;

    private boolean isScroll;
    private boolean isHot;
    private boolean isNew;

    public Goods() {
    }

    public Goods(int id, String name, String cover, String image1, String image2, String intro, int stock, float price, Type type) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.image1 = image1;
        this.image2 = image2;
        this.intro = intro;
        this.stock = stock;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public void setType_id(int type_id) {
        if (type==null) {
            type=new Type();
        }
        type.setId(type_id);
    }
    public void setType_name(String type_name) {
        if (type==null) {
            type=new Type();
        }
        type.setName(type_name);
    }

    public boolean getIsScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    public boolean getIsHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
