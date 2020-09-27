package com.szwg.dynamicdatasource.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;

public class FileUtil {
    public static void mkdir(String dir) {
        File dirFile = new File(dir);
        if (!dirFile.exists() && !dirFile.mkdir()) {
            throw new RuntimeException("无法创建指定文件夹:" + dir);
        }
    }

    public static void mkdirs(String dir) {
        File dirFile = new File(dir);
        if (!dirFile.exists() && !dirFile.mkdirs()) {
            throw new RuntimeException("无法创建指定文件夹:" + dir);
        }
    }


    /**
     * 将分隔符转换为平台相关的
     * @param loc
     * @return
     */
    public static String adjustSeparator(String loc) {
        return loc.replaceAll("\\\\|/", Matcher.quoteReplacement(File.separator));
    }

    /**
     * 自动增加分隔符
     * @param loc
     * @return
     */
    public static String autoAddSeparator(String loc) {
        return loc.endsWith(File.separator) ? loc : loc + File.separator;
    }

    /**
     * 自动调整与增加结尾分隔符
     * @param loc
     * @return
     */
    public static String autoAdjustAndAddSeparator(String loc) {
        return autoAddSeparator(adjustSeparator(loc));
    }


    /**
     * 连接多个目录
     * @param paths
     * @return
     */
    public static String concatDir(String... paths) {
//        StringBuilder sb = new StringBuilder(baseDir);
//        if (paths.length > 0) {
//            for (String dir : paths) {
//                if (StringUtils.isNotBlank(dir)) {
//                    sb.append(File.separator);
//                    sb.append(dir);
//                }
//            }
//        }
//        return sb.toString();
        return StringUtil.concat(File.separator,StringUtils::isNotBlank, paths);
    }


    /**
     * 将包格式转换成路径形式
     * @param packagePattern
     * @return
     */
    public static String convertPackageToDirPattern(String packagePattern) {
        return packagePattern.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }

    /**
     * 将路径形式转换成包形式
     * @param dirPattern
     * @return
     */
    public static String convertDirToPackagePattern(String dirPattern) {
        return dirPattern.replaceAll(Matcher.quoteReplacement(File.separator), ".");
    }
}
