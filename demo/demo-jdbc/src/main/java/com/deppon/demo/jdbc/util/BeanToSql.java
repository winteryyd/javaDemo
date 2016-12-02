package com.deppon.demo.jdbc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanToSql {
	public static String getInsertObjectSql(Object object) {
		// 定义一个sql字符串
        String sql = "insert into ";
        // 得到对象的类
        Class<?> c = object.getClass();
        // 得到对象中所有的方法
        Method[] methods = c.getMethods();
        // 得到对象中所有的属性
        Field[] fields = c.getFields();
        // 得到对象类的名字
        String cName = c.getName();
        // 从类的名字中解析出表名
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,
                cName.length());
        sql += tableName + "(";
        List<String> mList = new ArrayList<String>();
        List vList = new ArrayList();
        for (Method method : methods) {
            String mName = method.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) {
                String fieldName = mName.substring(3, mName.length());
                mList.add(fieldName);
                System.out.println("字段名字----->" + fieldName);
                try {
                    Object value = method.invoke(object, null);
                    System.out.println("执行方法返回的值：" + value);
                    if (value instanceof String) {
                        vList.add("\"" + value + "\"");
                        System.out.println("字段值------>" + value);
                    } else {
                        vList.add(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < mList.size(); i++) {
            if (i < mList.size() - 1) {
                sql += mList.get(i) + ",";
            } else {
                sql += mList.get(i) + ") values(";
            }
        }
        for (int i = 0; i < vList.size(); i++) {
            if (i < vList.size() - 1) {
                sql += vList.get(i) + ",";
            } else {
                sql += vList.get(i) + ")";
            }
        }
 
        return sql;
	}
}
