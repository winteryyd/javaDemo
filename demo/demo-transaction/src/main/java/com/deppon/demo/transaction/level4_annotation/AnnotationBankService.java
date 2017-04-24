package com.deppon.demo.transaction.level4_annotation;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.deppon.demo.transaction.BankService;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderBankDao;
import com.deppon.demo.transaction.level1_connection_holder.ConnectionHolderInsuranceDao;

public class AnnotationBankService implements BankService
{
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;
    private DataSource dataSource;

    public AnnotationBankService(){    	
    }
    
    public void init(DataSource dataSource){
    	connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }
    
    public AnnotationBankService(DataSource dataSource)
    {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    @Transactional
    public void transfer(final int fromId, final int toId, final int amount)
    {
    	System.out.println(fromId+"--give--"+toId+"--$"+amount);
        try
        {
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
