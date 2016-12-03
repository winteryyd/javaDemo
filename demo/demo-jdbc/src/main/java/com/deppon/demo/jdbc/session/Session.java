package com.deppon.demo.jdbc.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.deppon.demo.jdbc.connection.SingleThreadConnectionHolder;
import com.deppon.demo.jdbc.dataSource.DataSourceFactory;
import com.deppon.demo.jdbc.util.SessionUtil;

public class Session {
	private Connection conn = null;

	public Session() {
	}

	public Session(Connection conn) {
		this.conn = conn;
	}
	/**
	 * 根据配置创建数据库连接
	 */
	public void biuldConnection(){
		if (null == conn) {
			DataSource ds = DataSourceFactory.createDataSource();
			conn = SingleThreadConnectionHolder.getConnection(ds);
		}
	}
	/**
	 * 执行SQL语句
	 * @param sql
	 * @return
	 */
	public boolean execute(String sql){
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			if(null==prep)
				return false;
			return prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 根据entity删除表
	 * @param entityClass
	 * @return
	 */
	public boolean dropTable(Class<?> entityClass){
		String sql = "DROP TABLE IF EXISTS "+SessionUtil.getTableName(entityClass)+";";
		return execute(sql);
	}
	/**
	 * 根据entity创建表
	 * @param entityClass
	 * @return
	 */
	public boolean createTable(Object entity){
		dropTable(entity.getClass());
		String sql = null;
		try {
			sql = SessionUtil.getTableEntity(entity).getCreateTableSql();
			System.out.println(sql);
			if(null==sql)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return execute(sql);
	}
	/**
	 * 保存entity到数据库
	 * @param entity
	 * @return
	 */
	public boolean save(Object entity){
		
		String sql = null;
		try {
			sql = SessionUtil.getTableEntity(entity).getInsertSql();
			if(null==sql)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return execute(sql);
	}
	
}
