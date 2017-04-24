package com.deppon.demo.transaction.level3_transaction_proxy;

import javax.sql.DataSource;

import com.deppon.demo.transaction.BankService;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderBankDao;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderInsuranceDao;

public class BareBankService implements BankService
{
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public BareBankService(DataSource dataSource)
    {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    public void transfer(final int fromId, final int toId, final int amount)
    {
        try
        {
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
        } catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

	public void init(DataSource dataSource) {
	}
}
