package com.deppon.demo.transaction.level1_connection_holder;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.deppon.demo.jdbc.connection.SingleThreadConnectionHolder;

public class TransactionManager
{
    private DataSource dataSource;

    public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public TransactionManager(){
		
	}
	
	public TransactionManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public final void start() throws SQLException
    {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        System.out.println("事务开启。。。"+connection.getAutoCommit());
    }

    public final void commit() throws SQLException
    {
        Connection connection = getConnection();
        connection.commit();
        System.out.println("事务提交。。。");
    }

    public final void rollback()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.rollback();
            System.out.println("事务回滚。。。");
        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't rollback on connection[" + connection + "].", e);
        }
    }

    public final void close()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            SingleThreadConnectionHolder.removeConnection(dataSource);
            System.out.println("事务结束，关闭连接。。。");
        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
        }
    }

    private Connection getConnection() throws SQLException
    {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }
}
