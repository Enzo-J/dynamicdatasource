package com.szwg.dynamicdatasource.data.bo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 数据元数据
 */
@Data
public class DbInfo {
    /**
     * 某一个数据库名称
     */
    private String name;

    /**
     * 对应数据库的所有表信息
     */
    private List<TableInfo> tableInfos = Lists.newArrayList();

    public static DbInfo create(String name) {
        DbInfo dbInfo = new DbInfo();
        dbInfo.name = name;
        return dbInfo;
    }
}
