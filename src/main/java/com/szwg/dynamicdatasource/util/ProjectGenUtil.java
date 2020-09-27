package com.szwg.dynamicdatasource.util;

import com.google.common.collect.Maps;
import com.szwg.dynamicdatasource.config.CommonProp;
import com.szwg.dynamicdatasource.data.bo.ProjectProp;
import com.szwg.dynamicdatasource.data.bo.ProjectInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.Map;

/**
 * 项目生成工具
 */
public class ProjectGenUtil {

    public final static String COMMON_CONFIG_FTL = "java/commonConfig.ftl";
    public final static String SWAGGER_CONFIG_FTL = "java/swaggerConfig.ftl";
    public final static String CONTROLLER_FTL = "java/controller.ftl";
    public final static String SERVICE_FTL = "java/service.ftl";
    public final static String DAO_FTL = "java/dao.ftl";
    public final static String ENTITY_FTL = "java/entity.ftl";
    public final static String WRAPPER_OPER_FTL = "java/wrapperOper.ftl";
    public final static String RESULT_VO_FTL = "java/resultVo.ftl";
    public final static String MAPPER_FTL = "java/mapper.ftl";
    public final static String GLOBAL_EXCEPTION_HANDLER_FTL = "java/globalExceptionHandler.ftl";
    public final static String APPLICATION_LAUNCH_FTL = "java/applicationLaunch.ftl";

    public final static String POM_FTL = "pom.ftl";
    public final static String BOOTSTRAP_YAML_FTL = "bootstrapYaml.ftl";
    public final static String APPLICATION_YAML_FTL = "applicationYaml.ftl";
    public final static String DOCKERFILE_FTL = "dockerfile.ftl";

    /**
     * 创建一些基本的文件夹
     * project
     *    ---src
     *       ---main
     *           ---java
     *              ---com/szwg/datasource/${projectName.lowerCase}/config
     *              ---com/szwg/datasource/${projectName.lowerCase}/controller
     *              ---com/szwg/datasource/${projectName.lowerCase}/service
     *              ---com/szwg/datasource/${projectName.lowerCase}/dao
     *              ---com/szwg/datasource/${projectName.lowerCase}/handler
     *              ---com/szwg/datasource/${projectName.lowerCase}/data.entity
     *              ---com/szwg/datasource/${projectName.lowerCase}/data.vo
     *              ---com/szwg/datasource/${projectName.lowerCase}/data.dto
     *              ---com/szwg/datasource/${projectName.lowerCase}/controller
     *           ---resources
     *                    ---mapper
     */
    public static void mkBasicDir(ProjectInfo projectInfo) {
        FileUtil.mkdirs(projectInfo.getConfigDir());
        FileUtil.mkdirs(projectInfo.getControllerDir());
        FileUtil.mkdirs(projectInfo.getServiceDir());
        FileUtil.mkdirs(projectInfo.getDaoDir());
        FileUtil.mkdirs(projectInfo.getHandlerDir());
        FileUtil.mkdirs(projectInfo.getEntityDir());
        FileUtil.mkdirs(projectInfo.getVoDir());
        FileUtil.mkdirs(projectInfo.getDtoDir());
        FileUtil.mkdirs(projectInfo.getMapperDir());
    }


    /**
     * 通过模板生成文件
     * @param template
     * @param fileLoc
     */
    public static void writeToFile(Template template, String fileLoc, Object dataModel) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLoc)))) {
            template.process(dataModel, bw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据名称获取模板
     * @return
     */
    public static Template getTemplateByName(Configuration configuration, String name) throws IOException {
        return configuration.getTemplate(name, "utf-8");
    }

    /**
     * 获取CommonConfig.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getCommonConfigTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, COMMON_CONFIG_FTL);
    }

    /**
     * 获取SwaggerConfig.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getSwaggerConfigTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, SWAGGER_CONFIG_FTL);
    }

    /**
     * 获取controller.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getControllerTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, CONTROLLER_FTL);
    }

    /**
     * 获取service.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getServiceTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, SERVICE_FTL);
    }

    /**
     * 获取dao.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getDaoTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, DAO_FTL);
    }

    /**
     * 获取entity.java模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getEntityTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, ENTITY_FTL);
    }

    /**
     * 获取WrapperOper.java模板
     * @param configuration
     * @return
     */
    public static Template getWrapperOperTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, WRAPPER_OPER_FTL);
    }

    /**
     * 获取ResultVo.java模板
     * @param configuration
     * @return
     */
    public static Template getResultVoTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, RESULT_VO_FTL);
    }

    /**
     * 获取GlobalExceptionHandler.java模板
     * @param configuration
     * @return
     */
    public static Template getGlobalExceptionHandlerTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, GLOBAL_EXCEPTION_HANDLER_FTL);
    }

    /**
     * 获取applicationLaunch.java模板
     * @param configuration
     * @return
     */
    public static Template getApplicationLaunchTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, APPLICATION_LAUNCH_FTL);
    }

    /**
     * 获取mapper.xml模板
     * @param configuration
     * @return
     * @throws IOException
     */
    public static Template getMapperTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, MAPPER_FTL);
    }


    /**
     * 获取pom.xml模板
     * @param configuration
     * @return
     */
    public static Template getPomTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, POM_FTL);
    }


    /**
     * 获取bootstrap.yaml模板
     * @param configuration
     * @return
     */
    public static Template getBootstrapYamlTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, BOOTSTRAP_YAML_FTL);
    }

    /**
     * 获取application.yaml模板
     * @param configuration
     * @return
     */
    public static Template getApplicationYamlTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, APPLICATION_YAML_FTL);
    }

    /**
     * 获取Dockerfile模板
     * @param configuration
     * @return
     */
    public static Template getDockerfileTemplate(Configuration configuration) throws IOException {
        return getTemplateByName(configuration, DOCKERFILE_FTL);
    }



    /**
     * 生成单个文件
     */
    public static void writeSingleFile(Configuration configuration, ProjectProp projectProp, ProjectInfo projectInfo) throws IOException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("projectProp", projectProp);
        map.put("project", projectInfo);
        //ApplicationLaunch配置信息
        map.put("launchClassSimpleName", CommonProp.APPLICATION_LAUNCH);
        map.put("launchClassName", StringUtil.concatByDot(projectInfo.getJavaCodeBaseDotDir(), CommonProp.APPLICATION_LAUNCH));

        ProjectGenUtil.writeToFile(getCommonConfigTemplate(configuration), FileUtil.concatDir(projectInfo.getConfigDir(), CommonProp.COMMON_CONFIG), map);
        ProjectGenUtil.writeToFile(getSwaggerConfigTemplate(configuration), FileUtil.concatDir(projectInfo.getConfigDir(), CommonProp.SWAGGER_CONFIG), map);
        ProjectGenUtil.writeToFile(getWrapperOperTemplate(configuration), FileUtil.concatDir(projectInfo.getDtoDir(), CommonProp.WRAPPER_OPER), map);
        ProjectGenUtil.writeToFile(getResultVoTemplate(configuration), FileUtil.concatDir(projectInfo.getVoDir(), CommonProp.RESULT_VO), map);
        ProjectGenUtil.writeToFile(getGlobalExceptionHandlerTemplate(configuration), FileUtil.concatDir(projectInfo.getHandlerDir(), CommonProp.GLOBAL_EXCEPTION_HANDLER_LAUNCH), map);
        ProjectGenUtil.writeToFile(getApplicationLaunchTemplate(configuration), FileUtil.concatDir(projectInfo.getJavaCodeBaseDir(), CommonProp.APPLICATION_LAUNCH + ".java"), map);
        ProjectGenUtil.writeToFile(getBootstrapYamlTemplate(configuration), FileUtil.concatDir(projectInfo.getResourcesBaseDir(), CommonProp.BOOTSTRAP_YAML), map);
        ProjectGenUtil.writeToFile(getApplicationYamlTemplate(configuration), FileUtil.concatDir(projectInfo.getResourcesBaseDir(), CommonProp.APPLICATION_YAML), map);
        ProjectGenUtil.writeToFile(getPomTemplate(configuration), FileUtil.concatDir(projectInfo.getBaseDir(), CommonProp.POM), map);
        ProjectGenUtil.writeToFile(getDockerfileTemplate(configuration), FileUtil.concatDir(projectInfo.getBaseDir(), CommonProp.DOCKERFILE), map);
    }
}
