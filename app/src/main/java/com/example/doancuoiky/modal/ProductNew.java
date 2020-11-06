package com.example.doancuoiky.modal;

public class ProductNew {

    public String productID;
    public String productTypeID;
    public String productName;
    public String productDescription;
    public String specifications; // thong so ky thuat
    public int productPrice;
    public int productImage;

    public ProductNew(String productID, String productTypeID, String productName,
                      String productDescription, String specifications, int productPrice, int productImage) {
        this.productID = productID;
        this.productTypeID = productTypeID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.specifications = specifications;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
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

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
