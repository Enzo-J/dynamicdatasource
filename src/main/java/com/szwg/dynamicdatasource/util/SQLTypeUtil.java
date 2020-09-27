package com.szwg.dynamicdatasource.util;

import java.math.BigDecimal;
import java.sql.SQLType;
import java.sql.Time;
import java.sql.Types;
import java.util.Date;

public class SQLTypeUtil {

    /**
     * 进行类型转换，部分类型不支持
     * @param sqlType
     * @return
     */
    public static String convertSQLTypeToJavaType(Integer sqlType) {
        switch (sqlType) {
            case Types.BIGINT:
                return Long.class.getName();
            case Types.BIT:
                return Integer.class.getName();
            case Types.BOOLEAN:
                return Boolean.class.getName();
            case Types.CHAR:
                return String.class.getName();
            case Types.CLOB:
                return String.class.getName();
            case Types.DATE:
                return Date.class.getName();
            case Types.DECIMAL:
                return BigDecimal.class.getName();
            case Types.DOUBLE:
                return Double.class.getName();
            case Types.FLOAT:
                return Double.class.getName();
            case Types.INTEGER:
                return Integer.class.getName();
            case Types.NUMERIC:
                return Double.class.getName();
            case Types.LONGVARCHAR:
                return String.class.getName();
            case Types.LONGNVARCHAR:
                return String.class.getName();
            case Types.TIME:
                return Time.class.getName();
            case Types.TIMESTAMP:
                return Date.class.getName();
            case Types.TINYINT:
                return Integer.class.getName();
            case Types.VARCHAR:
                return String.class.getName();
            default:
                throw new RuntimeException("使用了不支持的SQLTypes:" + sqlType);
        }
    }
}
