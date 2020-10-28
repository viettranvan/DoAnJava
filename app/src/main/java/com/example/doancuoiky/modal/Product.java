package com.example.doancuoiky.modal;

import java.io.Serializable;

public class Product implements Serializable {

    private int resourceId;
    private String name;
    private String description;
    private int price;
    private boolean isAddToCart;

    public Product(int resourceId, String name, String description, int price) {
        this.resourceId = resourceId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAddToCart() {



        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
}
