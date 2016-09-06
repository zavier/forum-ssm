package com.forum.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public final class MD5Util {

    /**
     * 使用MD5进行加密
     * 
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        return new Md5Hash(str).toString();
    }
}
