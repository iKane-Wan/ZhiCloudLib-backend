package com.kane.utils;

/**
 * 包名: com.kane.utils
 * 说明：随机工具类
 * 创建时间： 2026-01-19
 */
public class RandomUtils {


    /**
     * 生成随机数字字符串
     * @return 随机数字字符串，范围为 0 到 999999
     */
    public static String getRandomNumber() {
        return String.valueOf((int) (Math.random() * 1000000));
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 字符串长度
     * @return 随机字符串，包含大写字母、小写字母和数字
     */
    public static String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * base.length());
            sb.append(base.charAt(index));
        }
        return sb.toString();
    }

}
