package ${project.entityDotDir};

import java.util.Map;
<#list table.importFields as importField>
import ${importField};
</#list>

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Maps;
import org.apache.ibatis.type.JdbcType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

<#--<#if table.remark != "">
/**
 * ${table.remark}
 */
@ApiModel("${table.remark}")
<#else>-->
@ApiModel
<#--</#if>-->
@Data
@TableName("${table.name}")
public class ${table.nameCamel} {
    private static Map<String, String> fieldToColumn = Maps.newHashMap();
    private static Map<String, Class> fieldToClz = Maps.newHashMap();
<#list table.fieldInfos as fieldInfo>
    <#if fieldInfo.remark != "">
    /**
     * ${fieldInfo.remark}
     */
    @ApiModelProperty(value = "${fieldInfo.remark}",  name = "${fieldInfo.columnNameCamel}"<#if fieldInfo.realTypeName == "Date">, example = "2020-01-01 00:00:00"<#elseif fieldInfo.realTypeName == "Time">, example = "10:10:10"<#else></#if>)
    <#else>
    @ApiModelProperty(dataType = "${fieldInfo.realTypeName}", name = "${fieldInfo.columnNameCamel}"<#if fieldInfo.realTypeName == "Date">, example = "2020-01-01 00:00:00"<#elseif fieldInfo.realTypeName == "Time">, example = "10:10:10"<#else></#if>)
    </#if>
    <#if fieldInfo.isPrimaryKey == 1 && fieldInfo.isAutoIncrement == "YES">
    @TableId(value = "${fieldInfo.columnName}", type = IdType.AUTO)
    <#elseif fieldInfo.isPrimaryKey == 1>
    @TableId(value = "${fieldInfo.columnName}")
    <#else>
    @TableField(value = "${fieldInfo.columnName}", jdbcType = JdbcType.${fieldInfo.typeName})
    </#if>
    <#if fieldInfo.realTypeName == "Date">
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    </#if>
    <#if fieldInfo.realTypeName == "Time">
    @JSONField(format = "HH:mm:ss")
    </#if>
    private ${fieldInfo.realTypeName} ${fieldInfo.columnNameCamel};
</#list>

    static {
        <#list table.fieldInfos as fieldInfo>
            fieldToColumn.put("${fieldInfo.columnNameCamel}", "${fieldInfo.columnName}");
            fieldToClz.put("${fieldInfo.columnNameCamel}", ${fieldInfo.realTypeName}.class);
        </#list>
    }

    /**
     * 获取列名
     * @param fieldName
     * @return
     */
    public static String getColumnByField(String fieldName) {
        return fieldToColumn.get(fieldName);
    }

    /**
     * 获取实际class类型
     * @param fieldName
     * @return
     */
    public static Class getClzByField(String fieldName) {
        return fieldToClz.get(fieldName);
    }

    public static Map<String, String> getFieldToColumn() {
        return fieldToColumn;
    }
}
