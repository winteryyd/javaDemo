package com.deppon.demo.transaction.level2_transaction_template;


import javax.sql.DataSource;

import com.deppon.demo.transaction.level1_connection_holder.TransactionManager;

public abstract class TransactionTemplate
{
    private TransactionManager transactionManager;

    protected TransactionTemplate(DataSource dataSource)
    {
        transactionManager = new TransactionManager(dataSource);
    }

    public void doJobInTransaction()
    {
        try
        {
            transactionManager.start();
            doJob();
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

    protected abstract void doJob() throws Exception;
}
