package com.glory.bianyitong.util;
import java.util.Date;
/**
 * Created by lucy on 2017/1/3.
 */
public class RelativeDateFormat {
    private static long minute = 1000 * 60;
    private static long hour = minute * 60;
    private static long day = hour * 24;
    private static long halfamonth = day * 15;
    private static long month = day * 30;

    public static String getDateDiff(long dateTimeStamp) {
        String result;
        long now = new Date().getTime();
        long diffValue = now - dateTimeStamp;
        if (diffValue < 0) {
            //toast("结束日期不能小于开始日期！");
        }
        long monthC = diffValue / month;
        long weekC = diffValue / (7 * day);
        long dayC = diffValue / day;
        long hourC = diffValue / hour;
        long minC = diffValue / minute;
        if (monthC >= 1) {
            result = Integer.parseInt(monthC + "") + "个月前";
            return result;
        } else if (weekC >= 1) {
            result = Integer.parseInt(weekC + "") + "周前";
            return result;
        } else if (dayC >= 1) {
            result = Integer.parseInt(dayC + "") + "天前";
            return result;
        } else if (hourC >= 1) {
            result = Integer.parseInt(hourC + "") + "个小时前";
            return result;
        } else if (minC >= 1) {
            result = Integer.parseInt(minC + "") + "分钟前";
            return result;
        } else {
            result = "1分钟内";
            return result;
        }
    }
}