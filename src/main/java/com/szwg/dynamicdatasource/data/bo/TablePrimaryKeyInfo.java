package com.szwg.dynamicdatasource.data.bo;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表的主键信息
 */
@Data
public class TablePrimaryKeyInfo {
    /**
     * 主键列名
     */
    private String name;

    public static TablePrimaryKeyInfo create(ResultSet primaryKeyRS) throws SQLException {
        TablePrimaryKeyInfo tablePrimaryKeyInfo = new TablePrimaryKeyInfo();
        tablePrimaryKeyInfo.name = primaryKeyRS.getString("COLUMN_NAME");
        return tablePrimaryKeyInfo;
    }
}
