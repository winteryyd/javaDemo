package com.deppon.demo.jdbc.dataSource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceFactory
{

    private static final BasicDataSource dataSource = new BasicDataSource();

    static
    {
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/javademo?useUnicode=true&characterEncoding=utf-8");
    }

    public static DataSource createDataSource()
    {
        return dataSource;
    }
}