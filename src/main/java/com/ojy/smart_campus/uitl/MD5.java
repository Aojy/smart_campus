package com.ojy.smart_campus.uitl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String getMD5(String str) {
        try {
            char hexChars[] =
                    {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            byte[] bytes = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            char[] chars = new char[bytes.length * 2];
            int k = 0;
            int i = 0;
            while (i < bytes.length) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
                ++i;
            }
            return  new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！！" + e);
        }
    }
}
