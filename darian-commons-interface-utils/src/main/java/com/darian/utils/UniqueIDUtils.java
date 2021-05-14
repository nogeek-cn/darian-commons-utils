package com.darian.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueIDUtils {

    /**
     * 简单的增长的ID
     * 获取一个唯一的数值字符串，生成规则：前缀+5位随机数
     * prefix + 19 位，
     *
     * @param prefix
     * @return
     */
    private static String generateUniqueIDString(String prefix) {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");

        String randomStr = String.valueOf(ThreadLocalRandom.current().nextInt(100000));
        int zeroLength = 5 - randomStr.length();
        for (int i = 0; i < zeroLength; i++) {
            randomStr = "0" + randomStr;
        }
        return prefix + simple.format(date) + randomStr;
    }

}