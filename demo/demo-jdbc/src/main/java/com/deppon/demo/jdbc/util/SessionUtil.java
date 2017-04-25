package com.deppon.demo.jdbc.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.deppon.demo.jdbc.entity.ColumnEntity;
import com.deppon.demo.jdbc.entity.TableEntity;

public class SessionUtil {

	/**
	 * 根据传入entity获取表名
	 * 
	 * @param entityClass
	 * @return
	 */
	public static String getTableName(Class<?> entityClass) {
		String tableName = entityClass.getSimpleName();
		if (entityClass.isAnnotationPresent(Entity.class)) {
			Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
			String tempTableName = entityAnnotation.name();
			if (tempTableName != null && !"".equals(tempTableName))
				tableName = tempTableName;
		}
		return tableName;
	}

	/**
	 * 根据Java数据类型转换对应MySQL类型
	 * 
	 * @param javaType
	 * @return
	 */
	public static String javaTypeToMySqlType(String javaType) {
		String mySqlType = "";
		if ("int".equals(javaType) || "Integer".equals(javaType)) {
			mySqlType = "int";
		} else if ("String".equals(javaType)) {
			mySqlType = "varchar";
		} else if ("Date".equals(javaType)) {
			mySqlType = "date";
		}

		return mySqlType;
	}

	/**
	 * 根据MySQL字段类型判断是否需要填充长度
	 * 
	 * @param mySqlType
	 * @return
	 */
	public static Boolean isLength(String mySqlType) {
		switch (mySqlType) {
		case "int":
			return false;
		case "varchar":
			return true;
		case "date":
			return false;
		default:
			return false;
		}
	}

	/**
	 * 由entity组装TableEntity
	 * 
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	public static TableEntity getTableEntity(Object entity) {
		TableEntity t = new TableEntity();
		List<ColumnEntity> columnEntitys = new ArrayList<ColumnEntity>();
		Class<?> entityClass = entity.getClass();
		t.setTableName(getTableName(entityClass));
		Field[] fields = entityClass.getDeclaredFields();
		String primaryKeyColumn = "";
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID"))
				continue;
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setProperty(field.getName());
			columnEntity.setColumnName(field.getName());
			columnEntity.setType(javaTypeToMySqlType(field.getType()
					.getSimpleName()));
			try {
				field.setAccessible(true);
				columnEntity.setValue(field.get(entity));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			String getMethodName = "get"
					+ field.getName().substring(0, 1).toUpperCase()
					+ field.getName().substring(1);
			Method method = null;
			try {
				method = entityClass.getMethod(getMethodName, new Class[] {});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			// 同上判断这个方法有没有我们要找的annotation
			Annotation[] annotationMethod = method.getAnnotations();
			// 得到属性的注解
			Annotation[] annotationField = field.getAnnotations();
			Annotation[] annotations = null;
			if (annotationMethod.length != 0) {
				annotations = annotationMethod;
			} else if (annotationField.length != 0) {
				annotations = annotationField;
			}
			if (annotations != null) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof Id) {
						if (primaryKeyColumn.length() == 0) {
							primaryKeyColumn = field.getName();
						} else {
							primaryKeyColumn = primaryKeyColumn + ","
									+ field.getName();
						}
					}
					if (annotation instanceof Column) {
						Column column = (Column) annotation;
						columnEntity.setColumnName(column.name());
						if (isLength(columnEntity.getType())) {
							columnEntity.setLength(column.length());
						}
					}
				}
			}
			if ("id".equals(columnEntity.getColumnName())
					&& primaryKeyColumn.length() == 0) {
				primaryKeyColumn = columnEntity.getColumnName();
			}
			if (isLength(columnEntity.getType())) {
				if (columnEntity.getLength() == 0) {
					columnEntity.setLength(255);
				}
			}
			columnEntitys.add(columnEntity);
		}
		t.setPrimarykey(primaryKeyColumn);
		t.setColumnEntitys(columnEntitys);
		return t;
	}

	/**
	 * 由entityClass组装TableEntity
	 * 
	 * @param entityClass
	 * @return
	 */
	public static TableEntity getTableEntity(Class<?> entityClass) {
		TableEntity t = new TableEntity();
		List<ColumnEntity> columnEntitys = new ArrayList<ColumnEntity>();
		t.setTableName(getTableName(entityClass));
		Field[] fields = entityClass.getDeclaredFields();
		String primaryKeyColumn = "";
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID"))
				continue;
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setProperty(field.getName());
			columnEntity.setColumnName(field.getName());
			columnEntity.setType(javaTypeToMySqlType(field.getType()
					.getSimpleName()));

			String getMethodName = "get"
					+ field.getName().substring(0, 1).toUpperCase()
					+ field.getName().substring(1);
			Method method = null;
			try {
				method = entityClass.getMethod(getMethodName, new Class[] {});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			// 同上判断这个方法有没有我们要找的annotation
			Annotation[] annotationMethod = method.getAnnotations();
			// 得到属性的注解
			Annotation[] annotationField = field.getAnnotations();
			Annotation[] annotations = null;
			if (annotationMethod.length != 0) {
				annotations = annotationMethod;
			} else if (annotationField.length != 0) {
				annotations = annotationField;
			}
			if (annotations != null) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof Id) {
						if (primaryKeyColumn.length() == 0) {
							primaryKeyColumn = field.getName();
						} else {
							primaryKeyColumn = primaryKeyColumn + ","
									+ field.getName();
						}
					}
					if (annotation instanceof Column) {
						Column column = (Column) annotation;
						columnEntity.setColumnName(column.name());
						if (isLength(columnEntity.getType())) {
							columnEntity.setLength(column.length());
						}
					}
				}
			}
			if ("id".equals(columnEntity.getColumnName())
					&& primaryKeyColumn.length() == 0) {
				primaryKeyColumn = columnEntity.getColumnName();
			}
			if (isLength(columnEntity.getType())) {
				if (columnEntity.getLength() == 0) {
					columnEntity.setLength(255);
				}
			}
			columnEntitys.add(columnEntity);
		}
		t.setPrimarykey(primaryKeyColumn);
		t.setColumnEntitys(columnEntitys);
		return t;
	}

	public static Field[] getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		Set<String> set = new HashSet<>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Field[] flds = clazz.getDeclaredFields();
			int i=0;
			for (Field field : flds) {// 获取bean的属性和值
				if (!set.contains(field.getName())&&!field.getName().equals("serialVersionUID")) {
					set.add(field.getName());
					fields.add(i, field);
					i++;
				}
			}
		}
		return (Field[]) fields.toArray(new Field[fields.size()]);
	}
}
