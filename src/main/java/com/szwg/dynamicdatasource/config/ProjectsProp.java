package com.szwg.dynamicdatasource.config;

import com.szwg.dynamicdatasource.data.bo.ProjectProp;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@RefreshScope
@Component
@Data
@ConfigurationProperties(prefix = "config")
public class ProjectsProp {

    private Map<String, ProjectProp> project;

    public ProjectProp getByName(String dbName) {
        ProjectProp projectProp = project.get(dbName);
        if (projectProp == null) {
            throw new RuntimeException("该dbName:" + dbName + "不存在，请在配置文件或者nacos中显式指定");
        }
        return projectProp;
    }
}
