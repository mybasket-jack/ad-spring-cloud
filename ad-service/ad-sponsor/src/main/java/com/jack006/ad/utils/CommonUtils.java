package com.jack006.ad.utils;

import com.jack006.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * 通用工具类
 *
 * @Author jack
 * @Since 1.0 2020/2/14 21:22
 */
public class CommonUtils {

    private static String[] parsepatterns = { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"};

    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseString2Date(String dateString) throws AdException{
        try {
            return DateUtils.parseDate(dateString, parsepatterns);
        } catch (Exception ex) {
            throw new AdException(ex.getMessage());
        }
    }
}
