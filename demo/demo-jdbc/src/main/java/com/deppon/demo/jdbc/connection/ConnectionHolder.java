package com.deppon.demo.jdbc.connection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHolder
{
    private Map<DataSource, Connection> connectionMap = new HashMap<DataSource, Connection>();

    public Connection getConnection(DataSource dataSource)
    {
        Connection connection = connectionMap.get(dataSource);
        try {
			if (connection == null || connection.isClosed())
			{
			    connection = dataSource.getConnection();
			    connectionMap.put(dataSource, connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return connection;
    }

    public void removeConnection(DataSource dataSource)
    {
        connectionMap.remove(dataSource);
    }
}
