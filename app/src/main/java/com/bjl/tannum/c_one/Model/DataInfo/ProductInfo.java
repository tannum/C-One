package com.bjl.tannum.c_one.Model.DataInfo;

/**
 * Created by tannum on 12/7/2016 AD.
 */

public class ProductInfo {

    private int id;
    private String name;
    private int price;
    private String description;

    public ProductInfo(int id ,String name, int  price, String description) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
