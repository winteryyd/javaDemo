package com.deppon.demo.transaction.level4_annotation;

import javax.sql.DataSource;

import com.deppon.demo.transaction.BankService;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderBankDao;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderInsuranceDao;

public class AnnotationBankService implements BankService
{
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public AnnotationBankService(DataSource dataSource)
    {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    @Transactional
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
}
