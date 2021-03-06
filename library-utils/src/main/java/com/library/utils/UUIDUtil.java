package com.library.utils;

import java.util.UUID;

/**
 * 生成唯一标识的字符串
 */
public class UUIDUtil {
    public static String createUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(createUUID());
    }
}
