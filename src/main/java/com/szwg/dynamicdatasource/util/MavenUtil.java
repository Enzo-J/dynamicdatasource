package com.szwg.dynamicdatasource.util;

import com.szwg.dynamicdatasource.config.CommonProp;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Collections;

/**
 * 对生成的项目进行编译
 */
@Slf4j
public class MavenUtil {
    public static void build(String projectLoc) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(projectLoc + File.separator + CommonProp.POM));
        //如果是windows,且没有指定dockerHost，不要执行dockerfile:push
        //程序所运行的linux服务器上有docker.sock，不用指定dockerHost，只需要指定harbor的http或者https地址即可
        String osName = System.getProperty("os.name");
        if (!osName.toLowerCase().contains("windows")){
            request.setGoals(Collections.singletonList("clean compile package dockerfile:push"));
            Invoker invoker = new DefaultInvoker();
            try {
                invoker.execute(request);
                log.info("对项目进行编译打包推送harbor成功,项目路径:{}", projectLoc);
            } catch (MavenInvocationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
    }
}
