package com.szwg.dynamicdatasource.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Scanner;


/**
 * 运行时编译工具类
 */
public class CompileUtil {

    public static Object compile(String fileLoc, String targetLoc, String cp) {
        Class clz = compileForClz(fileLoc, targetLoc, cp);
        if (clz != null) {
            try {
                return clz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Class compileForClz(String fileLoc, String targetLoc, String cp) {
        if (StringUtils.isBlank(targetLoc)) {
            targetLoc = getDefaultTargetLoc();
        }

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        int status = javac.run(null, null, null, "-cp", cp, "-d", targetLoc, fileLoc);
        if (status == 0) {
            try {
                String clzLoc = getPackage(fileLoc) + "."  + getClzName(fileLoc);
                clzLoc = clzLoc.trim();
                Class clz = Class.forName(clzLoc);//返回与带有给定字符串名的类 或接口相关联的 Class 对象。
                return clz;
            } catch (Exception e) {
            }
        }
        return null;
    }


    public static String getDefaultTargetLoc() {
        return System.getProperty("user.dir") + "\\target\\classes\\";
    }

    public static String getPackage(String fileLoc) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileLoc)))) {
            String line = null;
            while (StringUtils.isNotBlank((line = br.readLine()))) {
                if (line.startsWith("package")) {
                    return line.replace("package", "").replace(";", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getClzName(String fileLoc) {
        File file = new File(fileLoc);
        if (file.exists()) {
            try {
                String loc = file.getCanonicalPath();
                int separatorIndex = loc.lastIndexOf(File.separator);
                int dotIndex = loc.lastIndexOf(".");
                return loc.substring(separatorIndex + 1, dotIndex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        Object object = compile("d:\\AlTest.java", null, "E:\\mvnrepo\\org\\springframework\\spring-context\\5.2.8.RELEASE\\spring-context-5.2.8.RELEASE.jar");
//        System.out.println(object);
//        Object object2 = compile("d:\\AlTestController.java", null,
//                getDefaultTargetLoc() + ";E:\\mvnrepo\\org\\springframework\\spring-beans\\5.2.8.RELEASE\\spring-beans-5.2.8.RELEASE.jar;E:\\mvnrepo\\org\\springframework\\spring-web\\5.2.8.RELEASE\\spring-web-5.2.8.RELEASE.jar");
//        System.out.println(object2);
//    }
}
