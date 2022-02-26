package com.wjl.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <h1>时间处理工具类</h1>
 *
 * <p>对时间进行格式化、转换成字符串类型</p>
 *
 * @author: wjl
 * @date: 2022/1/8 9:38
 * @version: v1.0
 */
public class DateUtil {

    /**
     * <h2>把 {@code Date} 类型转成 {@code String} 类型变量并返回</h2>
     *
     * @param date date
     * @return string
     */
    public static String convertDateToString(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().substring(0, 19);
    }

    /**
     * <h2>把 {@code Date} 类型变量转成 {@code yyyy-MM-dd'T'HH:mm:ss } 的 {@code LocalDateTime} 类型变量并返回</h2>
     *
     * @param date date
     * @return localDateTime
     */
    public static LocalDateTime dateToLocalDateTimeWithoutMill(Date date) {
        String s = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().substring(0, 19);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

}
