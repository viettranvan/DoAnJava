package com.example.doancuoiky;

import com.example.doancuoiky.modal.Cart;
import com.example.doancuoiky.modal.Order;
import com.example.doancuoiky.modal.Product;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GlobalVariable {
    public static boolean isLogin = false;

    public static ArrayList<Order> arrayOrder;
    public static ArrayList<Cart> arrayCart;
    public static ArrayList<Product> arrarProduct;


    public static final String PASSWORD_PATTERN = "^" +
                                "(?=.*[0-9])" +         //at least 1 digit
                                "(?=.*[a-z])" +         //at least 1 lower case letter
                                "(?=.*[A-Z])" +         //at least 1 upper case letter
//                                "(?=.*[a-zA-Z])" +      //any letter
                                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                                "(?=\\S+$)" +           //no white spaces
                                ".{8,30}" +               //at least 8 characters
                                "$";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_PATTERN  = "0[0-9]{9}";
}

