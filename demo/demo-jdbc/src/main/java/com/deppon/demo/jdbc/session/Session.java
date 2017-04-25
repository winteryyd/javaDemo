package com.deppon.demo.jdbc.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deppon.demo.jdbc.connection.SingleThreadConnectionHolder;
import com.deppon.demo.jdbc.dataSource.DataSourceFactory;
import com.deppon.demo.jdbc.entity.ColumnEntity;
import com.deppon.demo.jdbc.entity.TableEntity;
import com.deppon.demo.jdbc.util.SessionUtil;
@Component
public class Session {
	
	private static final Logger logger = LoggerFactory.getLogger(Session.class);
	
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
	 * 执行查询SQL语句
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql){
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			if(null==prep)
				return null;
			return prep.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 执行DDL SQL语句
	 * @param sql
	 * @return
	 */
	public boolean executeUpdate(String sql){
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			if (prep.executeUpdate() > 0)
				return true;
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
		logger.info(sql);
		return execute(sql);
	}
	/**
	 * 根据entity创建表
	 * @param entityClass
	 * @return
	 */
	public boolean createTable(Class<?> entityClass){
		dropTable(entityClass);
		String sql = null;
		try {
			sql = SessionUtil.getTableEntity(entityClass).getCreateTableSql();
			logger.info(sql);
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
			logger.info(sql);
			if(null==sql)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return execute(sql);
	}
	/**
	 * 根据Id返回实体
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityClass,Object id){
		String sql = null;
		TableEntity tableEntity = null;
		try {
			tableEntity = SessionUtil.getTableEntity(entityClass);
			sql = tableEntity.getSelectSQL();
			if(null==sql)
				return null;
			sql = sql+id;
			logger.info(sql);
			ResultSet result = executeQuery(sql);
			result.next();
			// 把数据组拼到对象中去
			T entity = entityClass.newInstance();
			
			for(int i=0;i<tableEntity.getColumnEntitys().size();i++){
				ColumnEntity columnEntity = tableEntity.getColumnEntitys().get(i);
				Object value = result.getObject(columnEntity.getColumnName());
				BeanUtils.setProperty(entity, columnEntity.getProperty(), value);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * 更新实体数据
	 * @param entity
	 * @return
	 */
	public boolean update(Object entity){
		String sql = null;
		try {
			sql = SessionUtil.getTableEntity(entity).getUpdateSql();
			logger.info(sql);
			if(null==sql)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeUpdate(sql);
	}
	/**
	 * 删除实体数据
	 * @param entity
	 * @return
	 */
	public boolean delete(Object entity){
		String sql = null;
		try {
			sql = SessionUtil.getTableEntity(entity).getDeleteSql();
			logger.info(sql);
			if(null==sql)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeUpdate(sql);
	}

	public void close() {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}
}
