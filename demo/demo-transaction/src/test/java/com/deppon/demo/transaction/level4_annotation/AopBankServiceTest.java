package com.deppon.demo.transaction.level4_annotation;

import static junit.framework.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.demo.transaction.BankFixture;
import com.deppon.demo.transaction.BankService;

public class AopBankServiceTest extends BankFixture{
	BankService bankService = null;
	
	@Before
	public void init(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-transation.xml");
		bankService = (BankService)context.getBean("annotationBankService");
		DataSource dataSource = (DataSource)context.getBean("dataSource");
		bankService.init(dataSource);
	}
	
	
	@Test
    public void transferSuccess() throws SQLException
    {
		bankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws SQLException
    {
        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
