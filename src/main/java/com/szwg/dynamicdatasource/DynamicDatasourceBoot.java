package com.szwg.dynamicdatasource;

import freemarker.template.TemplateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableDiscoveryClient
public class DynamicDatasourceBoot {
    public static void main(String[] args) throws IOException, TemplateException, SQLException {
//        FileUtil.delFile("E:\\workspace\\Java\\dynamicdatasource\\target\\classes\\com\\szwg\\dynamicdatasource\\component\\AlTest.class");
//        FileUtil.delFile("E:\\workspace\\Java\\dynamicdatasource\\target\\classes\\com\\szwg\\dynamicdatasource\\component\\AlTest1.class");
//        FileUtil.delFile("E:\\workspace\\Java\\dynamicdatasource\\target\\classes\\com\\szwg\\dynamicdatasource\\controller\\AlTestController.class");
//        FileUtil.delFile("E:\\workspace\\Java\\dynamicdatasource\\target\\classes\\com\\szwg\\dynamicdatasource\\controller\\AlTestController1.class");
        SpringApplication.run(DynamicDatasourceBoot.class);

//        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
//        configuration.setDirectoryForTemplateLoading(new File("E:\\workspace\\Java\\dynamicdatasource\\target\\classes\\template"));
//        configuration.setDefaultEncoding("UTF-8");
//        Template template = configuration.getTemplate("/config/swaggerConfig.ftl");
//        System.out.println(template);
//
//        List<TableInfo> tableInfoList = TableUtil._genTableInfo(ConnectionUtil.getConnection(ProjectProp.getDefault()), null);
//        for (TableInfo tableInfo : tableInfoList) {
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("table", tableInfo);
//            template.process(map, new OutputStreamWriter(new FileOutputStream("d:/output/" + tableInfo.getNameCamel() + ".java")));
//        }
    }
}
