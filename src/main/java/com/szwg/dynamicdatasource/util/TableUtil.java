package com.szwg.dynamicdatasource.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mysql.cj.protocol.Resultset;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.szwg.dynamicdatasource.data.bo.*;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据库表工具类
 */
public class TableUtil {
//    public static void main(String[] args) throws SQLException {
//        List<TableInfo> tableInfos = _genTableInfo(ConnectionUtil.getConnection(ProjectProp.getDefault()), "app_version");
//        System.out.println(JSONObject.toJSONString(tableInfos));
//    }


    /**
     * 生成某一个数据库的所有表信息
     * @param connection
     * @param tableNames
     * @return
     * @throws SQLException
     */
    public static DbInfo genDbInfo(Connection connection, String... tableNames) throws SQLException {
        DbInfo dbInfo = DbInfo.create(connection.getCatalog());
        if (tableNames.length > 0) {
            for (String tableName : tableNames) {
                dbInfo.getTableInfos().addAll(_genTableInfo(connection, tableName));
            }
        } else {
            dbInfo.getTableInfos().addAll(_genTableInfo(connection, null));
        }
        return dbInfo;
    }


    /**
     * 生成多表表信息
     * @param connection
     * @param tableNames
     * @return
     * @throws SQLException
     */
    public static List<TableInfo> genTableInfo(Connection connection, String... tableNames) throws SQLException {
        List<TableInfo> tableInfos = Lists.newArrayList();
        if (tableNames.length > 0) {
            for (String tableName : tableNames) {
                tableInfos.addAll(_genTableInfo(connection, tableName));
            }
        }
        return tableInfos;
    }

    /**
     * 生成单表或多表(使用通配符%、_)表信息
     * @param connection
     * @param tableName
     * @return
     * @throws SQLException
     */
    public static List<TableInfo> _genTableInfo(Connection connection, String tableName) throws SQLException {
        List<TableInfo> tableInfos = Lists.newArrayList();
        String dbName = connection.getCatalog();
        ResultSet tableRS = connection.getMetaData().getTables(dbName, null, tableName, new String[]{"TABLE"});
        while (tableRS.next()) {
            TableInfo tableInfo = TableInfo.create(tableRS);
            ResultSet primaryKeyRS = connection.getMetaData().getPrimaryKeys(dbName, null, tableInfo.getName());
            while (primaryKeyRS.next()) {
                tableInfo.addTablePrimaryKeyInfo(TablePrimaryKeyInfo.create(primaryKeyRS));
            }

            ResultSet columnRS = connection.getMetaData().getColumns(dbName, null, tableInfo.getName(), null);
            while (columnRS.next()) {
                TableFieldInfo tableFieldInfo = TableFieldInfo.create(columnRS);
                tableInfo.getFieldInfos().add(tableFieldInfo);
            }
            
            tableInfo.processFieldMapping();
            tableInfos.add(tableInfo);
        }
        return tableInfos;
    }
}
