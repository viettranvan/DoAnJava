package com.example.doancuoiky.modal;

import android.content.Intent;

import java.lang.ref.SoftReference;

import javax.xml.namespace.QName;

public class Cart {

    private int cartProductImg;
    private String name;
    private String description;
    private int price;
    private String count;

    public Cart(int cartProductImg, String name, String description, int price, String count) {
        this.cartProductImg = cartProductImg;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
    }

    public int getCartProductImg() {
        return cartProductImg;
    }

    public void setCartProductImg(int cartProductImg) {
        this.cartProductImg = cartProductImg;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
