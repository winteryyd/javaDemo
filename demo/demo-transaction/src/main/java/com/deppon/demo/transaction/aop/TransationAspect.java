package com.deppon.demo.transaction.aop;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.deppon.demo.transaction.level1_connection_holder.TransactionManager;


@Aspect
//@Component
public class TransationAspect {
	
	@Resource(name="transactionManager")
	private TransactionManager transactionManager;
	
	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Pointcut("@annotation(com.deppon.demo.transaction.level4_annotation.Transactional)")
	public void transationAdvice() {
	}
	
	@Around("transationAdvice()")
    public Object transation(ProceedingJoinPoint pjp) throws Throwable{
		transactionManager.start();
        Object result = null;
        try
        {
            result = pjp.proceed();
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
        return result;
    }
	
}
