package com.fcant.regex;

/**
 * com.fcant.regex.com.fcant.regex
 *
 * @author Fcscanf
 * @description
 * @date 下午 15:41 2019-01-06/0006
 */
public class regex {
    public static void main(String[] args) throws Exception {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

        String value = "aaa";  // 长度不够
        System.out.println(value.matches(regex));

        value = "1111aaaa1111aaaaa";  // 太长
        System.out.println(value.matches(regex));

        value = "111111111"; // 纯数字
        System.out.println(value.matches(regex));

        value = "aaaaaaaaa"; // 纯字母
        System.out.println(value.matches(regex));

        value = "####@@@@#"; // 特殊字符
        System.out.println(value.matches(regex));

        value = "1111aaaa";  // 数字字母组合
        System.out.println(value.matches(regex));

        value = "aaaa1111"; // 数字字母组合
        System.out.println(value.matches(regex));

        value = "aa1111aa";    // 数字字母组合
        System.out.println(value.matches(regex));

        value = "11aaaa11";    // 数字字母组合
        System.out.println(value.matches(regex));

        value = "aa11aa11"; // 数字字母组合
        System.out.println(value.matches(regex));
    }
}
