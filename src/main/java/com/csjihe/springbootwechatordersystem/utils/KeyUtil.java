package com.csjihe.springbootwechatordersystem.utils;


import java.util.Random;

/**
 * Utility class generating Database key
 */
public class KeyUtil {

    /**
     * 声称唯一primary key
     * format: time + randomNumber
     * @return
     */
    public static synchronized String genUniqueKey() {


        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000; // 6-bit random number

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
