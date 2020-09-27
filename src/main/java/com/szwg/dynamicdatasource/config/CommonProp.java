package com.szwg.dynamicdatasource.config;

import com.szwg.dynamicdatasource.data.bo.ProjectInfo;
import com.szwg.dynamicdatasource.util.FileUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@RefreshScope
@Component
@Data
@ConfigurationProperties(prefix = "config.common")
public class CommonProp {

    public static String M2_PROJECT_JAVA = "src/main/java";
    public static String M2_PROJECT_RESOURCES = "src/main/resources";
    public final static String CONFIG_PATH = "config";
    public final static String CONTROLLER_PATH = "controller";
    public final static String SERVICE_PATH = "service";
    public final static String DAO_PATH = "dao";
    public static String ENTITY_PATH = "data/entity";
    public static String VO_PATH = "data/vo";
    public static String DTO_PATH = "data/dto";
    public static String HANDLER_PATH = "handler";
    public final static String MAPPER_PATH = "mapper";

    /**
     * CommonConfig类
     */
    public final static String COMMON_CONFIG = "CommonConfig.java";
    /**
     * swagger类
     */
    public final static String SWAGGER_CONFIG = "SwaggerConfig.java";

    /**
     * WrapperOper类
     */
    public final static String WRAPPER_OPER = "WrapperOper.java";
    /**
     * ResultVo类
     */
    public final static String RESULT_VO = "ResultVO.java";
    /**
     * 全局异常拦截处理
     */
    public final static String GLOBAL_EXCEPTION_HANDLER_LAUNCH = "GlobalExceptionHandler.java";
    /**
     * 启动类
     */
    public final static String APPLICATION_LAUNCH = "ApplicationLaunch";
    /**
     * bootstrap文件
     */
    public final static String BOOTSTRAP_YAML = "bootstrap.yml";
    /**
     * yaml文件
     */
    public final static String APPLICATION_YAML = "application.yml";
    /**
     * pom文件
     */
    public final static String POM = "pom.xml";
    /**
     * Dockerfile文件
     */
    public final static String DOCKERFILE = "Dockerfile";


    private String defaultLoc;
    private String packagePrefix;
    private String logLoc;
    private String port;
    private String nacosServerAddr;
    private String nacosUsername;
    private String nacosPassword;
    private String harborServerAddr;
    private String harborProjectName;
    private String harborUsername;
    private String harborPassword;


    private String javaCodePackage;
    private String resourcePackage;

    @PostConstruct
    public void init() {
        defaultLoc = FileUtil.adjustSeparator(defaultLoc);
        M2_PROJECT_JAVA = FileUtil.adjustSeparator(M2_PROJECT_JAVA);
        M2_PROJECT_RESOURCES = FileUtil.adjustSeparator(M2_PROJECT_RESOURCES);

        //data目录调整目录分隔符为相关平台的
        ENTITY_PATH = FileUtil.adjustSeparator(ENTITY_PATH);
        VO_PATH = FileUtil.adjustSeparator(VO_PATH);
        DTO_PATH = FileUtil.adjustSeparator(DTO_PATH);

        javaCodePackage = FileUtil.concatDir(M2_PROJECT_JAVA, FileUtil.convertPackageToDirPattern(packagePrefix));
        resourcePackage = M2_PROJECT_RESOURCES;
    }
    /**
     * 获取项目的基本地址
     * @param projectName
     * @return
     */
    public String getProjectLoc(String projectName) {
        return FileUtil.concatDir(defaultLoc, projectName);
    }


//    /**
//     * 获取java代码基本目录的路径
//     * @param projectName
//     * @return
//     */
//    public String getProjectJavaBaseDir(String projectName) {
//        return FileUtil.concatDir(defaultLoc, projectName, javaCodePackage);
//    }
//
//    /**
//     * 获取生成项目的resource目录路径
//     * @param projectName
//     * @return
//     */
//    public String getProjectResourceBaseDir(String projectName) {
//        return FileUtil.concatDir(defaultLoc, projectName, resourcePackage);
//    }


    /**
     * 生成对应的项目信息
     * @param name
     * @return
     */
    public ProjectInfo genProjectInfo(String name) {
        return ProjectInfo.create(this, name);
    }
}
