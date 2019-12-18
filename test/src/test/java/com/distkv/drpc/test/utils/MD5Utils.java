package com.distkv.drpc.test.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

  public static String MD5(String s) {
    char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    byte[] btInput = s.getBytes();
    MessageDigest mdInst;
    try {
      mdInst = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException(e);
    }

    mdInst.update(btInput);
    byte[] md = mdInst.digest();
    int j = md.length;
    char str[] = new char[j * 2];
    int k = 0;
    for (int i = 0; i < j; i++) {
      byte byte0 = md[i];
      str[k++] = hexDigits[byte0 >>> 4 & 0xf];
      str[k++] = hexDigits[byte0 & 0xf];
    }
    return new String(str);
  }

}