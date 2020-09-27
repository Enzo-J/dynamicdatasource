package com.szwg.dynamicdatasource.data.bo;

import lombok.Data;

import java.sql.Connection;

@Data
public class DbConnection {
    private Connection connection;
    private String dbName;
}
