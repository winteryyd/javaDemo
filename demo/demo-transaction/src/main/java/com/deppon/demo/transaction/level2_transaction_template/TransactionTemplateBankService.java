package com.deppon.demo.transaction.level2_transaction_template;


import javax.sql.DataSource;

import com.deppon.demo.transaction.BankService;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderBankDao;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderInsuranceDao;

public class TransactionTemplateBankService implements BankService
{
    private DataSource dataSource;
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public TransactionTemplateBankService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    public void transfer(final int fromId, final int toId, final int amount)
    {
        new TransactionTemplate(dataSource)
        {
            @Override
            protected void doJob() throws Exception
            {
                connectionHolderBankDao.withdraw(fromId, amount);
                connectionHolderInsuranceDao.deposit(toId, amount);
            }
        }.doJobInTransaction();
    }
}
