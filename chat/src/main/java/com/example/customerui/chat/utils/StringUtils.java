package com.example.customerui.chat.utils;

/**
 * Created by MrDing on 2016/11/27.
 */

public class StringUtils {

    private static StringBuffer buffer = new StringBuffer();


    public static String reverse(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        String result = buffer.append(str).reverse().toString();
        buffer.setLength(0);
        return result;
    }


}
