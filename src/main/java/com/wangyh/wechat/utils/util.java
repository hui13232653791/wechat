package com.wangyh.wechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class util {

    /**
     * 计算时间差：日期s1-日期s2
     * @param s1 日期s1
     * @param s2 日期s2
     * @return 相差天数
     */
    public static int fun(String s1,String s2){
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取Date
        Date t1 = null;
        Date t2 = null;
        try {
            t1 = simpleDateFormat.parse(s1);
            t2 = simpleDateFormat.parse(s2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获取时间戳
        Long time1 = t1.getTime();
        Long time2 = t2.getTime();
        Long num = time2- time1;
        Long day= num/24/60/60/1000;
        //返回相差天数
        return day.intValue();
    }

    /**
     * 计算生日
     * @param myBirthday
     * @return 返回距生日天数
     */
    public static int fun2(String myBirthday){
        //指定格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        try {
            cBirth.setTime(simpleDateFormat.parse(myBirthday)); // 设置生日
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        return days;
    }

}
