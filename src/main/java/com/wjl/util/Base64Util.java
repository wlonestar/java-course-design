package com.wjl.util;

import java.util.Base64;

/**
 * <h1>密码加密工具类</h1>
 *
 * <p>通过Base64编码方式将密码进行加密，安全性不高</p>
 *
 * @author: wjl
 * @date: 2022/1/8 9:09
 * @version: v1.0
 */
public class Base64Util {

    /**
     * 加密方法
     *
     * @param key key
     * @return string
     */
    public static String encrypt(String key) {
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

}
