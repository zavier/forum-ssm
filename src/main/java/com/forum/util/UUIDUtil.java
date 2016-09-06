package com.forum.util;

import java.util.UUID;

public final class UUIDUtil {

    /**
     * 获取随机的uuid作为主键
     * 
     * @return 生成的主键
     */
    public static String getUUIDPrimaryKey() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
