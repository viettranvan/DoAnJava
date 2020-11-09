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
    public static ArrayList<Product> arrayProduct;
    public static ArrayList<Product> arrayMobile;
    public static ArrayList<Product> arrayLaptop;

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
    public static final int INDEX_BIRTHDAY      = 11;


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
    public static final String PHONE_PATTERN  = "0[0-9]{9,10}";


    private static final String localhost = "http://192.168.2.105:5000/";
    public static final String LOGIN_URL = localhost + "api/login";
    public static final String USER_INFO_URL = localhost + "api/user";
    public static final String USER_SIGN_UP = localhost + "api/user";
    public static final String USER_UPDATE = localhost + "api/update-user";


    public static String  validateNameFirstUpperCase(String string){
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }


}

