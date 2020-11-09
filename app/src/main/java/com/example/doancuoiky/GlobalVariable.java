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

    public static String TOKEN = null;
    public static ArrayList<String> arrayProfile;
    public static final int INDEX_ID_USER       = 0;
    public static final int INDEX_EMAIL         = 1;
    public static final int INDEX_LOGIN_NAME    = 2;
    public static final int INDEX_USER_NAME     = 3;
    public static final int INDEX_ADDRESS       = 4;
    public static final int INDEX_CITIZEN_IDENTIFICATION = 5;
    public static final int INDEX_PHONE_NUMBER  = 6;
    public static final int INDEX_GENDER        = 7;
    public static final int INDEX_ACC_CREATE    = 8;
    public static final int INDEX_AVATAR        = 9;
    public static final int INDEX_RATE          = 10;

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


    public static final String LOGIN_URL = "http://192.168.2.105:5000/api/login";
    public static final String USER_INFO_URL = "http://192.168.2.105:5000/api/user";


}

