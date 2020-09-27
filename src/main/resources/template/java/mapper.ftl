<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${project.daoDotDir}.${table.nameCamel}Mapper">
    <sql id="Base_Column_List">
        <#list table.fieldInfos as fieldInfo>`${fieldInfo.columnName}`<#if fieldInfo_has_next>,</#if></#list>
    </sql>
    <#--没有单主键-->
    <sql id="Base_Column_List_Without_pk">
        <trim suffixOverrides=",">
        <#list table.fieldInfos as fieldInfo><#if fieldInfo.isPrimaryKey == 0>`${fieldInfo.columnName}`<#if fieldInfo_has_next>,</#if></#if></#list>
        </trim>
    </sql>
    <#if table.pkAutoIncrement == 1>
    <insert id="saveForId"  useGeneratedKeys="true" keyProperty="${table.pkFieldName}" keyColumn="${table.pkColumnName}" parameterType="${project.entityDotDir}.${table.nameCamel}">
        insert into ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list table.fieldInfos as fieldInfo>
                <if test="${fieldInfo.columnNameCamel} != null">
                    `${fieldInfo.columnName}`,
                </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list table.fieldInfos as fieldInfo>
                <if test="${fieldInfo.columnNameCamel} != null">
                    <#noparse>#{</#noparse>${fieldInfo.columnNameCamel}<#noparse>}</#noparse>,
                </if>
            </#list>
        </trim>
    </insert>
    </#if>
</mapper>