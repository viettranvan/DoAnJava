package com.example.doancuoiky.modal;

import android.content.Intent;

import java.lang.ref.SoftReference;

import javax.xml.namespace.QName;

public class Cart {


    private String ID;
    private String TypeID;
    private String name;
    private String description;
    private String specifications; // thong so ky thuat
    private int price;
    private int cartProductImg;
    private int count;

    public Cart(String ID, String typeID, String name, String description, String specifications,
                int price, int cartProductImg, int count) {
        this.ID = ID;
        TypeID = typeID;
        this.name = name;
        this.description = description;
        this.specifications = specifications;
        this.price = price;
        this.cartProductImg = cartProductImg;
        this.count = count;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCartProductImg() {
        return cartProductImg;
    }

    public void setCartProductImg(int cartProductImg) {
        this.cartProductImg = cartProductImg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //    public Cart(int cartProductImg, String name, String description, int price, String count) {
//        this.cartProductImg = cartProductImg;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.count = count;
//    }

//    public int getCartProductImg() {
//        return cartProductImg;
//    }
//
//    public void setCartProductImg(int cartProductImg) {
//        this.cartProductImg = cartProductImg;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public String getCount() {
//        return count;
//    }
//
//    public void setCount(String count) {
//        this.count = count;
//    }
}
