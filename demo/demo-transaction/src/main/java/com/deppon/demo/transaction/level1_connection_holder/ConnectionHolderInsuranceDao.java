package com.deppon.demo.transaction.level1_connection_holder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.deppon.demo.jdbc.connection.SingleThreadConnectionHolder;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/7/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionHolderInsuranceDao
{
    private DataSource dataSource;

    public ConnectionHolderInsuranceDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void deposit(int insuranceId, int amount) throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);
        PreparedStatement selectStatement = connection.prepareStatement("SELECT INSURANCE_AMOUNT FROM INSURANCE_ACCOUNT WHERE INSURANCE_ID = ?");
        selectStatement.setInt(1, insuranceId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int previousAmount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();


        int newAmount = previousAmount + amount;
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE INSURANCE_ACCOUNT SET INSURANCE_AMOUNT = ? WHERE INSURANCE_ID = ?");
        updateStatement.setInt(1, newAmount);
        updateStatement.setInt(2, insuranceId);
        updateStatement.execute();

        updateStatement.close();
    }
}
