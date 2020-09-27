package com.szwg.dynamicdatasource.data.bo;

import com.szwg.dynamicdatasource.config.CommonProp;
import com.szwg.dynamicdatasource.util.FileUtil;
import com.szwg.dynamicdatasource.util.StringUtil;
import lombok.Data;

@Data
public class ProjectInfo {

    public final static String PROJECT_NAME_PREFIX = "Datasource";
    /**
     * 项目全名
     */
    private String projectFullName;
    /**
     * 项目全名小写
     */
    private String projectFNLowerCase;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目根目录
     */
    private String baseDir;
    /**
     * ${baseDir}/src/main/java
     * maven java目录，非java文件开始存在的目录
     */
    private String javaBaseDir;
    /**
     * ${baseDir}/src/main/resources
     */
    private String resourcesBaseDir;
    /**
     * ${baseDir}/${javaCodePackage}
     */
    private String javaCodeBaseDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}
     */
    private String javaCodeBaseDotDir;

    /**
     * ${baseDir}/${javaCodePackage}/${projectName}/controller
     */
    private String configDir;

    /**
     * ${packagePrefix}.${projectName.lowerCase}.controller
     */
    private String configDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/${projectName}/controller
     */
    private String controllerDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.controller
     */
    private String controllerDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/service
     */
    private String serviceDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.service
     */
    private String serviceDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/dao
     */
    private String daoDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.dao
     */
    private String daoDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/data/entity
     */
    private String entityDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.data.entity
     */
    private String entityDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/data/vo
     */
    private String voDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.data.vo
     */
    private String voDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/data/dto
     */
    private String dtoDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.data.dto
     */
    private String dtoDotDir;
    /**
     * ${baseDir}/${javaCodePackage}/handler
     */
    private String handlerDir;
    /**
     * ${packagePrefix}.${projectName.lowerCase}.handler
     */
    private String handlerDotDir;


    /**
     * ${baseDir}/src/main/resources/mapper
     */
    private String mapperDir;
    private String mapperLocation;
    private String logLoc;
    private String port;

    private String nacosServerAddr;
    private String nacosUsername;
    private String nacosPassword;
    private String harborServerAddr;
    private String harborProjectName;
    private String harborUsername;
    private String harborPassword;


    /**
     * 根据项目生成配置以及项目名称创建对应的项目相关信息
     * @param genConfig
     * @param projectName
     * @return
     */
    public static ProjectInfo create(CommonProp genConfig, String projectName) {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.projectName = StringUtil.convertToUpperCamel(projectName);
        projectInfo.projectFullName = PROJECT_NAME_PREFIX + projectInfo.getProjectName();
        String projectNameLowerCase = projectInfo.projectName.toLowerCase();
        projectInfo.projectFNLowerCase = projectInfo.projectFullName.toLowerCase();
        projectInfo.baseDir = genConfig.getProjectLoc(projectInfo.projectName);
        projectInfo.javaBaseDir = FileUtil.concatDir(projectInfo.baseDir, CommonProp.M2_PROJECT_JAVA);
        projectInfo.resourcesBaseDir = FileUtil.concatDir(projectInfo.baseDir, CommonProp.M2_PROJECT_RESOURCES);

        //java代码相关目录
        projectInfo.javaCodeBaseDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase);
        projectInfo.javaCodeBaseDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase);

        //config目录
        projectInfo.configDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.CONFIG_PATH);
        projectInfo.configDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.CONFIG_PATH));

        //controller, service,dao,data(entity,vo,dto)相关目录
        projectInfo.controllerDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.CONTROLLER_PATH);
        projectInfo.controllerDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.CONTROLLER_PATH));

        //service目录
        projectInfo.serviceDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.SERVICE_PATH);
        projectInfo.serviceDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.SERVICE_PATH));

        //dao目录
        projectInfo.daoDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.DAO_PATH);
        projectInfo.daoDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.DAO_PATH));

        //data目录下
            //entity目录
        projectInfo.entityDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.ENTITY_PATH);
        projectInfo.entityDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.ENTITY_PATH));
            //vo目录
        projectInfo.voDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.VO_PATH);
        projectInfo.voDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.VO_PATH));
            //dto目录
        projectInfo.dtoDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.DTO_PATH);
        projectInfo.dtoDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.DTO_PATH));

        //handler目录
        projectInfo.handlerDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getJavaCodePackage(), projectNameLowerCase, CommonProp.HANDLER_PATH);
        projectInfo.handlerDotDir = StringUtil.concatByDot(genConfig.getPackagePrefix(), projectNameLowerCase, FileUtil.convertDirToPackagePattern(CommonProp.HANDLER_PATH));

        //杂项配置
        projectInfo.mapperDir = FileUtil.concatDir(projectInfo.baseDir, genConfig.getResourcePackage(), CommonProp.MAPPER_PATH);
        projectInfo.mapperLocation = StringUtil.concat("/", null, "", CommonProp.MAPPER_PATH, "**", "*.xml");
        projectInfo.logLoc = genConfig.getLogLoc();
        projectInfo.port = genConfig.getPort();
        projectInfo.nacosServerAddr = genConfig.getNacosServerAddr();
        projectInfo.nacosUsername = genConfig.getNacosUsername();
        projectInfo.nacosPassword = genConfig.getNacosPassword();
        projectInfo.harborServerAddr = genConfig.getHarborServerAddr();
        projectInfo.harborProjectName = genConfig.getHarborProjectName();
        projectInfo.harborUsername = genConfig.getHarborUsername();
        projectInfo.harborPassword = genConfig.getHarborPassword();

        return projectInfo;
    }
}
