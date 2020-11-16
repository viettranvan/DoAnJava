package com.example.doancuoiky.modal;

import java.io.Serializable;

public class Product implements Serializable {

    private String productID;
    private String productTypeID;
    private String productName;
    private String productDescription;
    private int productPrice;
    private String productImage;
    private int count;
    private boolean isAddToCart;

    public Product(String productID, String productTypeID, String productName,
                   String productDescription, int productPrice, String productImage) {
        this.productID = productID;
        this.productTypeID = productTypeID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public Product(String productID, String productTypeID, String productName, String productDescription,
                   int productPrice, String productImage, int count) {
        this.productID = productID;
        this.productTypeID = productTypeID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //    public Product(String productID, String productTypeID, String productName,
//                String productDescription, String specifications, int productPrice, int productImage, int count) {
//        this.productID = productID;
//        this.productTypeID = productTypeID;
//        this.productName = productName;
//        this.productDescription = productDescription;
//        this.specifications = specifications;
//        this.productPrice = productPrice;
//        this.productImage = productImage;
//        this.count = count;
//    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }



    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
}
