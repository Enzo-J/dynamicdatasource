package com.szwg.dynamicdatasource.data.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.szwg.dynamicdatasource.util.SQLTypeUtil;
import com.szwg.dynamicdatasource.util.StringUtil;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 表的元数据
 */
@Data
public class TableInfo {
    public final static String CONTROLLER_SUFFIX = "Controller.java";
    public final static String SERVICE_SUFFIX = "Service.java";
    public final static String DAO_SUFFIX = "Mapper.java";
    public final static String ENTITY_SUFFIX = ".java";
    public final static String MAPPER_SUFFIX = "Mapper.xml";
    /**
     * 表名
     */
    private String name;
    /**
     * 首字母大写的驼峰表名
     */
    private String nameCamel;

    /**
     * 第一个字母为小写
     */
    private String nameCamelFirstLower;
    /**
     * 表备注
     */
    private String remark;
    /**
     * 表的列信息
     */
    private List<TableFieldInfo> fieldInfos = Lists.newArrayList();
    /**
     * 表的主键信息
     */
    private List<TablePrimaryKeyInfo> primaryKeyInfos = Lists.newArrayList();

    /**
     * 主键数量
     */
    private Integer primaryKeyCount = 0;

    /**
     * entity需要引入的类
     */
    private Set<String> importFields = Sets.newHashSet();

    /**
     * 是否是单列自增主键
     */
    private Integer pkAutoIncrement = 0;

    private String pkColumnName;
    private String pkFieldName;

    public static TableInfo create(ResultSet tableRS) throws SQLException {
        TableInfo tableInfo = new TableInfo();
        tableInfo.name = tableRS.getString("TABLE_NAME");
        tableInfo.remark = StringUtil.replaceAllByBlankForLineBreak(tableRS.getString("REMARKS"));
        tableInfo.nameCamel = StringUtil.convertToUpperCamel(tableInfo.name);
        tableInfo.nameCamelFirstLower = StringUtil.convertToLowerCamel(tableInfo.name);
        return tableInfo;
    }


    /**
     * 处理列映射
     */
    public void processFieldMapping() {
        Set<String> primaryKeySet = primaryKeyInfos.stream().map(TablePrimaryKeyInfo::getName).collect(Collectors.toSet());
        for (TableFieldInfo fieldInfo : fieldInfos) {
            String fullName = SQLTypeUtil.convertSQLTypeToJavaType(fieldInfo.getDataType());
            int dotIndex = fullName.lastIndexOf(".");
            if (!fullName.startsWith("java.lang")) {
                importFields.add(fullName);
            }
            //mybatis plus不支持多主键,只支持单主键
            if (primaryKeySet.size() == 1 && primaryKeySet.contains(fieldInfo.getColumnName())) {
                fieldInfo.setIsPrimaryKey(1);
                pkAutoIncrement = "YES".equals(fieldInfo.getIsAutoIncrement()) ? 1 : 0;
                pkColumnName = fieldInfo.getColumnName();
                pkFieldName = fieldInfo.getColumnNameCamel();
            }
            fieldInfo.setRealTypeName(fullName.substring(dotIndex + 1, fullName.length()));
            fieldInfo.convertTypeNameToMyBatisJdbcType();
        }
    }

    /**
     * controller文件名称
     * @return
     */
    public String getControllerFileName() {
        return nameCamel + CONTROLLER_SUFFIX;
    }

    /**
     * service文件名称
     * @return
     */
    public String getServiceFileName() {
        return nameCamel + SERVICE_SUFFIX;
    }

    /**
     * dao文件名称
     * @return
     */
    public String getDaoFileName() {
        return nameCamel + DAO_SUFFIX;
    }

    /**
     * entity文件名称
     * @return
     */
    public String getEntityFileName() {
        return nameCamel + ENTITY_SUFFIX;
    }

    /**
     * mapper文件名称
     * @return
     */
    public String getMapperFileName() {
        return nameCamel + MAPPER_SUFFIX;
    }

    /**
     * 增加主键列信息，
     * @param tablePrimaryKeyInfo
     */
    public void addTablePrimaryKeyInfo(TablePrimaryKeyInfo tablePrimaryKeyInfo) {
        primaryKeyInfos.add(tablePrimaryKeyInfo);
        primaryKeyCount++;
    }
}
