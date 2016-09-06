package com.forum.util;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CommonUtil {

    /**
     * 获取随机的uuid作为主键
     * 
     * @return 生成的主键
     */
    public static String getUUIDPrimaryKey() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

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
