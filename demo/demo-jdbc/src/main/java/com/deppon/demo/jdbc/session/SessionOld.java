package com.deppon.demo.jdbc.session;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.deppon.demo.jdbc.connection.SingleThreadConnectionHolder;
import com.deppon.demo.jdbc.dataSource.DataSourceFactory;

/**
 * 
 * 说明：使用java的反射机制模拟hibernate的session
 * 
 * @author KeYuan
 * 
 */
public class SessionOld {
	private Connection conn = null;
	
	public Connection getConnection() throws SQLException{
		if(null==conn){
			DataSource ds=DataSourceFactory.createDataSource();
			conn=SingleThreadConnectionHolder.getConnection(ds);
		}
		return conn;
	}
	/**
	 * 添加操作
	 */
	public boolean save(Object entity) throws SecurityException,NoSuchMethodException, IllegalArgumentException,IllegalAccessException, SQLException, ClassNotFoundException {
		StringBuffer insertSql = new StringBuffer("insert into ");
		StringBuffer insertSqlValue = new StringBuffer();
		LinkedList<Object> insertParams = new LinkedList<Object>();
		Class<?> entityClass = entity.getClass();
		String tableName = getTableName(entityClass);
		insertSql.append(tableName);
		Field[] fields = entityClass.getDeclaredFields();
		insertSql.append("(");
		insertSqlValue.append(" values(");
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			String columnName = field.getName();
			// 查找当前属性上面是否有annotation注解
			Object[] findAnnotationResult = findAnnotation(annotations);
			Boolean isAnnotaionOverField = (Boolean) findAnnotationResult[0];
			// 如果在field中上面没有找到annotation,继续到get属性上去找有没有annotation
			if (!isAnnotaionOverField) {
				// 拼接出field的get属性名
				String getMethodName = "get"
						+ columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1);
				Method method = entityClass.getMethod(getMethodName,
						new Class[] {});
				// 同上判断这个方法有没有我们要找的annotation
				annotations = method.getAnnotations();
				findAnnotationResult = findAnnotation(annotations);
				isAnnotaionOverField = (Boolean) findAnnotationResult[0];
			}
			// 判断通过前面两步操作有没有在当前的字段上面找到有效的annotation
			if (!isAnnotaionOverField)
				continue;
			// 到这步说明在当前的字段或字段get属性上面找到有效的annotation了
			// 拼接insert sql 语句
			String tempColumnName = (String) findAnnotationResult[1];
			if (tempColumnName != null && !"".equals(tempColumnName))
				columnName = tempColumnName;
			insertSql.append(columnName).append(",");// 前面列名部分
			insertSqlValue.append("?,"); // 后面?参数部分
			// 得到对应的字段值并记录,作为以后?部分值
			field.setAccessible(true);
			insertParams.add(field.get(entity));
		}
		insertSql.replace(insertSql.lastIndexOf(","), insertSql.length(), ")");
		insertSqlValue.replace(insertSqlValue.lastIndexOf(","), insertSqlValue
				.length(), ")");
		// 拼接两部分的sql
		insertSql.append(insertSqlValue);
		System.out.println(insertSql);
		// 执行添加操作了
		PreparedStatement prep = getConnection().prepareStatement(insertSql.toString());
		int i = 1;
		for (Object param : insertParams) {
			if (param instanceof Date) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = java.sql.Date.valueOf(dateFormat
						.format(param));
				prep.setDate(i, date);
			} else {
				prep.setObject(i, param);
			}
			i++;
		}
		if (prep.executeUpdate() > 0)
			return true;
		return false;
	}
	/**
	 * 得到表的真实名
	 */
	private String getTableName(Class<?> entityClass) {
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
	 * 查询字段或是属性上面有没有有效annotation
	 */
	private Object[] findAnnotation(Annotation[] annotations) {
		Object[] resurlt = new Object[] { false, null };
		if (annotations.length == 0)
			return resurlt;
		for (Annotation annotation : annotations) {
			// 我们假定当他找到下列标签中任何一个标签就认为是要与数据库映射的
			if (annotation instanceof Column) {
				resurlt[0] = true;
				Column column = (Column) annotation;
				String tempColumnName = column.name();
				if (tempColumnName != null && !"".equals(tempColumnName))
					resurlt[1] = tempColumnName;
			}
		}
		return resurlt;
	}

	/**
	 *  修改操作
	 */
	public boolean update(Object entity) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// update stuInfo stu set stu.stuName1='ddd' where stu.stuid=43
		StringBuffer updateSql = new StringBuffer("update ");
		LinkedList<Object> updateParams = new LinkedList<Object>();
		String primaryKeyColumn = "";
		Integer primaryParam = null;
		Class<?> entityClass = entity.getClass();
		String tableName = getTableName(entityClass);

		updateSql.append(tableName).append(" tab set ");
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			String columnName = field.getName();
			Annotation[] annotations = field.getAnnotations();
			// 判断是否是主键
			boolean isfindPrimarykey = false;
			for (Annotation annotation : annotations) {
				if (annotation instanceof Id) {
					primaryKeyColumn = field.getName();
					field.setAccessible(true);
					primaryParam = (Integer) field.get(entity);
					isfindPrimarykey = true;
					break;
				}

			}
			if (isfindPrimarykey)
				continue;
			Object[] findAnnotationResult = findAnnotation(annotations);
			boolean isAnnotaionOverField = (Boolean) findAnnotationResult[0];
			if (!isAnnotaionOverField) {
				String getMethodName = "get"
						+ columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1);
				Method method = entityClass.getMethod(getMethodName,
						new Class[] {});
				annotations = method.getAnnotations();
				findAnnotationResult = findAnnotation(annotations);
				isAnnotaionOverField = (Boolean) findAnnotationResult[0];
			}
			if (!isAnnotaionOverField)
				continue;
			String tempColumnName = (String) findAnnotationResult[1];
			if (tempColumnName != null && !"".equals(tempColumnName))
				columnName = tempColumnName;
			updateSql.append("tab.").append(columnName).append("=?,");
			field.setAccessible(true);
			updateParams.add(field.get(entity));
		}
		updateSql.replace(updateSql.lastIndexOf(","), updateSql.length(), "");
		updateSql.append(" where tab.").append(primaryKeyColumn).append("=?");
		System.out.println(updateSql);

		PreparedStatement prep = getConnection().prepareStatement(updateSql.toString());
		int i = 1;
		for (Object param : updateParams) {
			if (param instanceof Date) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = java.sql.Date.valueOf(dateFormat
						.format(param));
				prep.setDate(i, date);
			} else {
				prep.setObject(i, param);
			}
			i++;
		}
		prep.setInt(i, primaryParam);
		if (prep.executeUpdate() > 0)
			return true;
		return false;
	}

	/**
	 *  删除操作
	 */
	public boolean delete(Object entity) throws IllegalArgumentException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// delete from stuInfo stu where stu.stuid=43
		StringBuffer deleteSql = new StringBuffer("delete from ");
		Integer primaryParam = null;
		Class<?> entityClass = entity.getClass();
		String tableName = getTableName(entityClass);
		deleteSql.append(tableName).append(" tab ").append("where ");
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			boolean isfindPrimary = false;
			for (Annotation annotation : annotations) {
				if (annotation instanceof Id) {
					deleteSql.append("tab.").append(field.getName()).append(
							"=?");
					field.setAccessible(true);
					primaryParam = (Integer) field.get(entity);
					isfindPrimary = true;
					break;
				}
			}
			if (isfindPrimary)
				break;
		}
		System.out.println(deleteSql.toString());
		PreparedStatement prep = getConnection().prepareStatement(deleteSql.toString());
		prep.setInt(1, primaryParam);
		if (prep.executeUpdate() > 0)
			return true;
		return false;
	}

	/**
	 * 根据Id查询某个实体对象
	 */
	public <T> T get(Class<T> entityClass, Integer id)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InvocationTargetException {
		T entity = null;
		StringBuffer selectByIdSql = new StringBuffer("select * from ");
		String tableName = getTableName(entityClass);
		selectByIdSql.append(tableName).append(" tab where tab.");
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			boolean isfindPrimaryfield = false;
			String columnName = field.getName();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Id) {
					selectByIdSql.append(columnName).append("=?");
					isfindPrimaryfield = true;
					break;
				}
			}
			if (!isfindPrimaryfield) {
				String getMethodName = "get"
						+ columnName.substring(0, 1).toUpperCase()
						+ columnName.substring(1);
				Method getMethod = entityClass.getMethod(getMethodName,
						new Class[] {});
				annotations = getMethod.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof Id) {
						selectByIdSql.append(columnName).append("=?");
						isfindPrimaryfield = true;
						break;
					}
				}
			}
			if (isfindPrimaryfield)
				break;

		}
		System.out.println(selectByIdSql.toString());
		PreparedStatement prep = getConnection()
				.prepareStatement(selectByIdSql.toString());
		prep.setInt(1, id);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			entity = setData2Entity(entityClass, fields, result);
		}
		return entity;
	}
	/**
	 *装result中的数据据,用反射加入到对应的实体中 
	 */
	private <T> T setData2Entity(Class<T> entityClass, Field[] fields,
			ResultSet result) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SQLException,
			InvocationTargetException {
		// 把数据组拼到对象中去
		T entity = entityClass.newInstance();
		for (Field field : fields) {
			String fieldName = field.getName();
			String columnName = fieldName;
			Annotation[] annotations = field.getAnnotations();
			Object[] findAnnotationResult = findAnnotation(annotations);
			boolean isfindAnotation = (Boolean) findAnnotationResult[0];
			if (!isfindAnotation) {
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = entityClass.getMethod(getMethodName,
						new Class[] {});
				annotations = method.getAnnotations();
				findAnnotationResult = findAnnotation(annotations);
				isfindAnotation = (Boolean) findAnnotationResult[0];
			}
			String tempColumnName = (String) findAnnotationResult[1];
			if (tempColumnName != null && !"".equals(tempColumnName))
				columnName = tempColumnName;
			Object value = result.getObject(columnName);
			BeanUtils.setProperty(entity, fieldName, value);
		}
		return entity;
	}

	/**
	 * 分页查询所有记录
	 **/
	public <T> List<T> getPaging(Class<T> entityClass, int firstIndex,
			int maxResult) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		List<T> results = new ArrayList<T>();
		StringBuffer pageIngSql = new StringBuffer(
				"select * from (select rownum rn,tab.* from ");
		String tableName = getTableName(entityClass);
		pageIngSql.append(tableName).append(" tab ) where rn between ? and ?");
		System.out.println(pageIngSql.toString());
		PreparedStatement prep = getConnection().prepareStatement(pageIngSql.toString());

		prep.setInt(1, firstIndex);
		prep.setInt(2, firstIndex + maxResult);
		ResultSet result = prep.executeQuery();
		Field[] fields = entityClass.getDeclaredFields();
		while (result.next()) {
			T entity = setData2Entity(entityClass, fields, result);
			results.add(entity);
		}
		return results;
	}

	/**
	 * 得到总页数
	 */
	public <T> int getCount(Class<T> entityClass)
			throws ClassNotFoundException, SQLException {
		int count = 0;
		StringBuffer countSql = new StringBuffer("select count(*) count from ");
		String tableName = getTableName(entityClass);
		countSql.append(tableName);
		System.out.println(countSql.toString());
		PreparedStatement prep = getConnection().prepareStatement(countSql.toString());
		ResultSet result = prep.executeQuery();
		if (result.next()) {
			count = result.getInt("count");
		}
		return count;
	}
	
	
	/*//测试
	public static void main(String[] args) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, SQLException, ClassNotFoundException,
			InstantiationException, InvocationTargetException {
		Session session = new Session();
		Student student = new Student();
		student.setStuId(2);
		student.setStuName("hhhh");
		student.setBirthday(new Date());
		
		if (session.save(student)) {
			System.out.println("添加成功");
		} else {
			System.out.println("添加失败");
		}
		
		
		 * student.setStuId(43); 
		 * if (session.update(student)) {
		 *  System.out.println("修改成功"); 
		 * } 
		 * else {
		 *  System.out.println("修改失败");
		 *   }
		 

		
		 * student.setStuId(42); 
		 * if (session.delete(student)) {
		 *  System.out.println("删除成功");
		 *  } else { 
		 *  System.out.println("删除失败");
		 *   }
		 
		
		 * 根据id查询 student = session.get(Student.class, 41);
		 * System.out.println(student.getStuId() + " name:" +
		 * student.getStuName() + "  birthday:" + student.getBirthday());
		 

		
		 * 分页查询
		 
		int currentPage = 1;
		int maxResult = 2;
		int count = session.getCount(Student.class);
		int countPage = count / maxResult == 0 ? count / maxResult : count
				/ maxResult + 1;
		int firstIndex = (currentPage - 1) * maxResult+1;
		List<Student> pagingList = session.getPaging(Student.class,
				firstIndex, maxResult);
		System.out
				.println("总页数:" + countPage + " \t 当前第 " + currentPage + " 页");
		System.out.println("编号\t姓名\t出生年日");
		for (Student student2 : pagingList) {
			System.out.println(student2.getStuId() + "\t"
					+ student2.getStuName() + "\t" + student2.getBirthday());
		}
		
	}*/

}
