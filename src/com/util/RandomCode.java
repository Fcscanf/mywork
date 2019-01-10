package com.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * RandomCode
 *
 * @author Fcscanf
 * @description
 * @date 上午 8:34 2019-01-10/0010
 */
public class RandomCode {
    /**
     * 数字
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static final String SYMBOLS = "0123456789";

    // 字符串
    // private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random RANDOM = new SecureRandom();

    public static void main(String[] args) {
        System.out.println(getNonce_str());
    }

    /**
     * 获取长度为 6 的随机数字
     * @return 随机数字
     * @date 修改日志：由 space 创建于 2018-8-2 下午2:43:51
     */
    public static String getNonce_str() {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[6];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(nonceChars);
    }

}