package com.szwg.dynamicdatasource.util;

import com.szwg.dynamicdatasource.data.bo.ProjectProp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * 数据库连接工具类
 */
public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(ProjectProp projectProp) throws SQLException {
        try {
            return DriverManager.getConnection(projectProp.getUrl(), projectProp.getUsername(), projectProp.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
