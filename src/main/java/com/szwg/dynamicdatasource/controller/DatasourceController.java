package com.szwg.dynamicdatasource.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.szwg.dynamicdatasource.config.ProjectsProp;
import com.szwg.dynamicdatasource.config.CommonProp;
import com.szwg.dynamicdatasource.data.bo.ProjectProp;
import com.szwg.dynamicdatasource.data.bo.DbInfo;
import com.szwg.dynamicdatasource.data.bo.ProjectInfo;
import com.szwg.dynamicdatasource.data.bo.TableInfo;
import com.szwg.dynamicdatasource.data.vo.ResultVO;
import com.szwg.dynamicdatasource.util.*;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/datasource")
@RestController
public class DatasourceController {
    @Autowired
    private ProjectsProp projectsProp;
    @Autowired
    private Configuration configuration;
    @Autowired
    private CommonProp commonProp;


    /**
     * 生成整个项目
     * @param dbName
     * @return
     */
    @GetMapping("/genProject")
    public ResultVO genProject(String dbName) {
        if (StringUtils.isBlank(dbName)) {
            return ResultVO.error("请指定dbName");
        }
        Connection connection = null;
        try {
            ProjectProp projectProp = projectsProp.getByName(dbName);
            connection = ConnectionUtil.getConnection(projectProp);
            DbInfo dbInfo = TableUtil.genDbInfo(connection);
            gen(dbName, projectProp, dbInfo.getTableInfos());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error("生成失败:" + e.getMessage());
        } finally {
            ConnectionUtil.closeConnection(connection);
        }
        return ResultVO.success("根据dbName:" + dbName + "生成项目生成成功");
    }


    /**
     * 重新生成某些表
     * @param dbName
     * @return
     */
    @GetMapping("/genForTable")
    public ResultVO genForTable(String dbName, String tables) {
        if (StringUtils.isBlank(dbName) || StringUtils.isBlank(tables)) {
            return ResultVO.error("请指定dbName及tables");
        }
        tables = tables.trim();
        String[] tableArray;
        if (tables.startsWith("[")) {
            tableArray = JSONObject.parseArray(tables, String.class).toArray(new String[0]);
        } else {
            tableArray = tables.split(",");
        }
        Connection connection = null;
        try {
            ProjectProp projectProp = projectsProp.getByName(dbName);
            connection = ConnectionUtil.getConnection(projectProp);
            List<TableInfo> tableInfos = TableUtil.genTableInfo(connection, tableArray);
            gen(dbName, projectProp, tableInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error("生成失败:" + e.getMessage());
        } finally {
            ConnectionUtil.closeConnection(connection);
        }
        return ResultVO.success("根据dbName:" + dbName + "及tables:" + tables + "生成项目生成成功");
    }


    /**
     * 生成相关表的controller,service,mapper等等
     * @param dbName
     * @param projectProp
     * @param tableInfos
     * @throws IOException
     */
    private void gen(String dbName, ProjectProp projectProp, List<TableInfo> tableInfos) throws IOException {

        ProjectInfo projectInfo = commonProp.genProjectInfo(dbName);
        ProjectGenUtil.mkBasicDir(projectInfo);

        Template controllerTemplate = ProjectGenUtil.getControllerTemplate(configuration);
        Template serviceTemplate = ProjectGenUtil.getServiceTemplate(configuration);
        Template daoTemplate = ProjectGenUtil.getDaoTemplate(configuration);
        Template entityTemplate = ProjectGenUtil.getEntityTemplate(configuration);
        Template mapperTemplate = ProjectGenUtil.getMapperTemplate(configuration);
        ProjectGenUtil.writeSingleFile(configuration, projectProp, projectInfo);

        for (TableInfo tableInfo : tableInfos) {
            log.info("正在生成表{}的相关信息", tableInfo.getName());
            Map<String, Object> map = Maps.newHashMap();
            map.put("table", tableInfo);
            map.put("project", projectInfo);
            ProjectGenUtil.writeToFile(controllerTemplate, FileUtil.concatDir(projectInfo.getControllerDir(), tableInfo.getControllerFileName()), map);
            log.info("表{},生成{}成功", tableInfo.getName(), tableInfo.getControllerFileName());
            ProjectGenUtil.writeToFile(serviceTemplate, FileUtil.concatDir(projectInfo.getServiceDir(), tableInfo.getServiceFileName()), map);
            log.info("表{},生成{}成功", tableInfo.getName(), tableInfo.getServiceFileName());
            ProjectGenUtil.writeToFile(daoTemplate, FileUtil.concatDir(projectInfo.getDaoDir(), tableInfo.getDaoFileName()), map);
            log.info("表{},生成{}成功", tableInfo.getName(), tableInfo.getDaoFileName());
            ProjectGenUtil.writeToFile(entityTemplate, FileUtil.concatDir(projectInfo.getEntityDir(), tableInfo.getEntityFileName()), map);
            log.info("表{},生成{}成功", tableInfo.getName(), tableInfo.getEntityFileName());
            ProjectGenUtil.writeToFile(mapperTemplate, FileUtil.concatDir(projectInfo.getMapperDir(), tableInfo.getMapperFileName()), map);
            log.info("表{},生成{}成功", tableInfo.getName(), tableInfo.getMapperFileName());
        }

        //maven打包，上传harbor
        MavenUtil.build(projectInfo.getBaseDir());
    }
}
