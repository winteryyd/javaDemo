package com.deppon.demo.transaction.level4_annotation;

import static junit.framework.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import com.deppon.demo.transaction.BankFixture;
import com.deppon.demo.transaction.BankService;
import com.deppon.demo.transaction.level1_connection_holder.TransactionManager;

public class AnnotationBankServiceTest extends BankFixture
{
    @Test
    public void transferSuccess() throws SQLException
    {
        TransactionManager transactionManager = new TransactionManager(dataSource);
        TransactionEnabledAnnotationProxyManager transactionEnabledProxyManager = new TransactionEnabledAnnotationProxyManager(transactionManager);
        BankService bankService = new AnnotationBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);
        proxyBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws SQLException
    {
        TransactionEnabledAnnotationProxyManager transactionEnabledAnnotationProxyManager = new TransactionEnabledAnnotationProxyManager(new TransactionManager(dataSource));
        BankService bankService = new AnnotationBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledAnnotationProxyManager.proxyFor(bankService);

        int toNonExistId = 3333;
        proxyBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
