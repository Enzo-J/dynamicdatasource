package com.szwg.dynamicdatasource.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.sql.Time;
import java.util.function.Predicate;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 将字符串转换成驼峰形式的，
     * 确保第一个字母为小写
     * @param str
     * @return
     */
    public static String convertToLowerCamel(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        str = str.trim();
        str = str.replaceAll("\\-", "_");
        if (str.contains("_")) {
            str = str.toLowerCase();
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
        }
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, str);
    }


    /**
     * 转换成驼峰形式
     * @param str
     * @return
     */
    public static String convertToUpperCamel(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        str = str.trim();
        str = str.replaceAll("\\-", "_");
        if (str.contains("_")) {
            str = str.toLowerCase();
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, str);
    }

    /**
     * 转换成小写形式，没有_与-
     * @param str
     * @return
     */
    public static String convertToLowerCase(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return str.replaceAll("\\_", "").replaceAll("\\-", "").toLowerCase();
    }

    /**
     * 根据指定字符对字符数组进行连接，默认是空字符串
     * @param connectorStr
     * @param strs
     * @return
     */
    public static String concat(String connectorStr, Predicate<String> predicate, String... strs) {
        if (connectorStr == null) {
            connectorStr = "";
        }
        if (predicate == null) {
            predicate = s -> true;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (predicate.test(str)) {
                sb.append(str);
                sb.append(connectorStr);
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - connectorStr.length(), sb.length());
        }
        return sb.toString();
    }

    /**
     * 使用.进行连接
     * @param strs
     * @return
     */
    public static String concatByDot(String... strs) {
        return concat(".", null, strs);
    }


    /**
     * 替换指定字符
     * @param str
     * @param regex
     * @return
     */
    public static String replaceAll(String str, String regex, String replacement) {
        if (str == null) {
            return str;
        }
        return str.replaceAll(regex, replacement);
    }

    /**
     * 使用空字符串替换正则匹配的字符
     * @param str
     * @param regex
     * @return
     */
    public static String replaceAllByBlank(String str, String regex) {
        return replaceAll(str, regex, "");
    }


    /**
     * 使用空字符串替换回车和换行符
     * @return
     */
    public static String replaceAllByBlankForLineBreak(String str) {
        return replaceAll(str, "\\r|\\n", "");
    }

    public static void main(String[] args) {
        String str = "hello\nworld".replaceAll("\\r|\\n", "");
        System.out.println(str);
        System.out.println(new Time(22, 50, 50));
    }
}
