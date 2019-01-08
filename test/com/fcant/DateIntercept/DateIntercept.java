package com.fcant.DateIntercept;

/**
 * DateIntercept
 *
 * @author Fcscanf
 * @description
 * @date 上午 11:15 2019-01-08/0008
 */
public class DateIntercept {
    public static void main(String[] args) {
        String date = "2019-10-08 12:15:10";
        String date1 = "2019-10-08 12:19:10";
        String year = date.substring(0, 4);
        System.out.println(year);
        String month = date.substring(5, 7);
        System.out.println(month);
        String dairy = date.substring(8, 10);
        System.out.println(dairy);
        String hour = date.substring(11, 13);
        System.out.println(hour);
        String minute = date.substring(14, 16);
        System.out.println(minute);
        String second = date.substring(17, 19);
        System.out.println(second);
        long i = Long.parseLong(year + month + dairy + hour + minute + second);
        System.out.println(i);

        if (dateToLong(date) > dateToLong(date1)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public static long dateToLong(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String dairy = date.substring(8, 10);
        String hour = date.substring(11, 13);
        String minute = date.substring(14, 16);
        String second = date.substring(17, 19);
        long i = Long.parseLong(year + month + dairy + hour + minute + second);
        return i;
    }
}
