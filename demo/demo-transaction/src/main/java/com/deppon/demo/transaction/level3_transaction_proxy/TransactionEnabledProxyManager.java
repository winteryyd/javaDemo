package com.deppon.demo.transaction.level3_transaction_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.deppon.demo.transaction.level1_connection_holder.TransactionManager;

public class TransactionEnabledProxyManager
{
    private TransactionManager transactionManager;

    public TransactionEnabledProxyManager(TransactionManager transactionManager)
    {

        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object)
    {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new TransactionInvocationHandler(object, transactionManager));
    }
}

class TransactionInvocationHandler implements InvocationHandler
{
    private Object proxy;
    private TransactionManager transactionManager;

    TransactionInvocationHandler(Object object, TransactionManager transactionManager)
    {
        this.proxy = object;
        this.transactionManager = transactionManager;
    }

    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        transactionManager.start();
        Object result = null;
        try
        {
            result = method.invoke(proxy, objects);
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
