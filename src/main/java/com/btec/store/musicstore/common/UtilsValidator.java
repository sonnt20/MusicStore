package com.btec.store.musicstore.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilsValidator {
    public static String md5(String input) throws NoSuchAlgorithmException {
        String result = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        result = hexString.toString();
        return result;
    }
}
