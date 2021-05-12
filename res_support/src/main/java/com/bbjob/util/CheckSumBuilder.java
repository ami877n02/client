package com.bbjob.util;

import java.security.MessageDigest;
import java.util.Random;

public class CheckSumBuilder {
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }
    public static String getAppKey(String appName,String salt,String createTime) {
        return encode("sha1",  appName + salt + createTime);
    }
    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    public static String randomHexString(int len)  {  
        try {  
            StringBuffer result = new StringBuffer();  
            for(int i=0;i<len;i++) {  
                result.append(Integer.toHexString(new Random().nextInt(16)));  
            }  
            return result.toString().toUpperCase();  
              
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
              
        }  
        return null;  
          
    } 
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
