package com.deppon.demo.jdbc.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.deppon.demo.jdbc.DataSourceFactory;
import com.deppon.demo.jdbc.SingleThreadConnectionHolder;

public class SqlToBean {
	 public Object getObject(String className, int Id) throws SQLException {
	        // 得到表名字
	        String tableName = className.substring(className.lastIndexOf(".") + 1,
	                className.length());
	        // 根据类名来创建Class对象
	        Class c = null;
	        try {
	            c = Class.forName(className);
	 
	        } catch (ClassNotFoundException e1) {
	 
	            e1.printStackTrace();
	        }
	        // 拼凑查询sql语句
	        String sql = "select * from " + tableName + " where Id=" + Id;
	        System.out.println("查找sql语句：" + sql);
	        // 获得数据库链接
	        DataSource ds=DataSourceFactory.createDataSource();
	        Connection con=SingleThreadConnectionHolder.getConnection(ds);
	        // 创建类的实例
	        Object obj = null;
	        try {
	 
	            Statement stm = con.createStatement();
	            // 得到执行查寻语句返回的结果集
	            ResultSet set = stm.executeQuery(sql);
	            // 得到对象的方法数组
	            Method[] methods = c.getMethods();
	            // 遍历结果集
	            while (set.next()) {
	                obj = c.newInstance();
	                // 遍历对象的方法
	                for (Method method : methods) {
	                    String methodName = method.getName();
	                    // 如果对象的方法以set开头
	                    if (methodName.startsWith("set")) {
	                        // 根据方法名字得到数据表格中字段的名字
	                        String columnName = methodName.substring(3,
	                                methodName.length());
	                        // 得到方法的参数类型
	                        Class[] parmts = method.getParameterTypes();
	                        if (parmts[0] == String.class) {
	                            // 如果参数为String类型，则从结果集中按照列名取得对应的值，并且执行改set方法
	                            method.invoke(obj, set.getString(columnName));
	                        }
	                        if (parmts[0] == int.class) {
	                            method.invoke(obj, set.getInt(columnName));
	                        }
	                    }
	 
	                }
	            }
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return obj;
	    }
}
