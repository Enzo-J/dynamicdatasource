package com.szwg.dynamicdatasource.data.bo;

import com.szwg.dynamicdatasource.util.StringUtil;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表的列信息
 */
@Data
public class TableFieldInfo {
    /**
     * 列名
     */
    private String columnName;

    /**
     * 首字母小写的驼峰列名
     */
    private String columnNameCamel;
    /**
     * 列类型
     */
    private Integer dataType;
    /**
     * 列类型名
     */
    private String typeName;

    /**
     * 转换成java中的类型
     */
    private String realTypeName;
    /**
     * 是否可空
     */
    private Integer nullable;
    /**
     * 列注释
     */
    private String remark;
    /**
     * 是否自动生成
     */
    private String isAutoIncrement;

    private Integer isPrimaryKey = 0;


    ///  System.out.println("DATA_TYPE:" + columnRS.getInt("DATA_TYPE") + "\n" +
    //                            ",TYPE_NAME:" + columnRS.getString("TYPE_NAME") + "\n" +
    //                            ",COLUMN_NAME:" + columnRS.getString("COLUMN_NAME") + "\n" +
    //                            ",TABLE_NAME:" + columnRS.getString("TABLE_NAME") + "\n" +
    //                            ",DECIMAL_DIGITS:" + columnRS.getInt("DECIMAL_DIGITS") + "\n" +
    //                            ",NUM_PREC_RADIX:" + columnRS.getString("NUM_PREC_RADIX") + "\n" +
    //                            ",NULLABLE:" + columnRS.getInt("NULLABLE") + "\n" +
    //                            ",REMARKS:" + columnRS.getString("REMARKS") + "\n" +
    //                            ",COLUMN_DEF:" + columnRS.getString("COLUMN_DEF") + "\n" +
    //                            ",IS_NULLABLE:" + columnRS.getString("IS_NULLABLE") + "\n" +
    //                            ",IS_AUTOINCREMENT:" + columnRS.getString("IS_AUTOINCREMENT") + "\n" +
    //                            ",IS_GENERATEDCOLUMN:" + columnRS.getString("IS_GENERATEDCOLUMN")
    //                    );
    public static TableFieldInfo create(ResultSet columnRS) throws SQLException {
        TableFieldInfo tableFieldInfo = new TableFieldInfo();
        tableFieldInfo.columnName = columnRS.getString("COLUMN_NAME");
        tableFieldInfo.dataType = columnRS.getInt("DATA_TYPE");
        tableFieldInfo.typeName = columnRS.getString("TYPE_NAME");
        tableFieldInfo.nullable = columnRS.getInt("NULLABLE");
        tableFieldInfo.remark = StringUtil.replaceAllByBlankForLineBreak(columnRS.getString("REMARKS"));
        tableFieldInfo.isAutoIncrement = columnRS.getString("IS_AUTOINCREMENT");
        tableFieldInfo.columnNameCamel = StringUtil.convertToLowerCamel(tableFieldInfo.columnName);
        return tableFieldInfo;
    }


    public void convertTypeNameToMyBatisJdbcType() {
        typeName = JdbcType.forCode(dataType).toString();
    }
}
