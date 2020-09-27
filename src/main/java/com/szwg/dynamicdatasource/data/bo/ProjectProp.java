package com.szwg.dynamicdatasource.data.bo;

import lombok.Data;

@Data
public class ProjectProp {
    /**
     * 在yaml文件中指定完整url
     */
    private String url;
    /**
     * 在yaml文件中指定username
     */
    private String username;
    /**
     * 在yaml文件中指定password
     */
    private String password;

    public static ProjectProp getDefault() {
        ProjectProp projectProp = new ProjectProp();
        projectProp.url = "jdbc:mysql://192.168.1.188:3306/fscommand_screen?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        projectProp.username = "root";
        projectProp.password = "szwg2018";
        return projectProp;
    }

}
