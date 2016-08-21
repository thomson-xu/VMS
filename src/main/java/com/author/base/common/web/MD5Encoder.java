package com.author.base.common.web;

/**
 * Created by Administrator on 2016/8/9.
 */

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Encoder {
    public MD5Encoder() {
    }

    public static final String encoder(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            BASE64Encoder encoder = new BASE64Encoder();
            mdTemp.update(e);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return encoder.encode((new String(str)).getBytes());
        } catch (Exception var11) {
            return null;
        }
    }

}
