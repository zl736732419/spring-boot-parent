package com.zheng.springboot.shiro.utils;

import java.util.Date;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/6/16 16:09
 */
public class DateUtil {
    /**
     * 判断前者时间是否比后者小
     * @param source
     * @param dest
     * @return
     */
    public static boolean before(Date source, Date dest) {
        if (!Optional.ofNullable(source).isPresent() || !Optional.ofNullable(dest).isPresent()) {
            return false;
        }
        
        long sourceTime = source.getTime();
        long destTime = source.getTime();
        return sourceTime < destTime;
    }

    /**
     * 判断前者时间是否比后者大
     * @param source
     * @param dest
     * @return
     */
    public static boolean after(Date source, Date dest) {
        if (!Optional.ofNullable(source).isPresent() || !Optional.ofNullable(dest).isPresent()) {
            return false;
        }

        long sourceTime = source.getTime();
        long destTime = source.getTime();
        return sourceTime > destTime;
    }
}
