package com.deppon.demo.jdbc.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TableEntity {
	//主键
	private String primarykey;
	//表名
	private String tableName;
	//列信息
	private List<ColumnEntity> columnEntitys;

	public List<ColumnEntity> getColumnEntitys() {
		return columnEntitys;
	}

	public void setColumnEntitys(List<ColumnEntity> columnEntitys) {
		this.columnEntitys = columnEntitys;
	}

	public String getPrimarykey() {
		return primarykey;
	}

	public void setPrimarykey(String primarykey) {
		this.primarykey = primarykey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String toString() {
		System.out.println("TableEntity[tableName:" + tableName
				+ "  primarykey:" + primarykey + "]");
		for (ColumnEntity colunmn : columnEntitys) {
			System.out.println(colunmn.toString());
		}
		return "";
	}

	/**
	 * 获取建表语句
	 * @return
	 */
	public String getCreateTableSql() {
		if(null==tableName||"".equals(tableName))
			return null;
		if (columnEntitys.size() == 0)
			return null;
		
		StringBuffer sql = new StringBuffer("CREATE TABLE ");
		sql.append(tableName).append("(\n");
		for (int i = 0; i < columnEntitys.size(); i++) {
			ColumnEntity ce = columnEntitys.get(i);
			if (ce.getLength() == 0) {
				sql.append(ce.getColumnName() + " " + ce.getType());
			} else {
				sql.append(ce.getColumnName() + " " + ce.getType() + "("
						+ ce.getLength() + ")");
			}
			if (i != columnEntitys.size() - 1) {
				sql.append(",\n");
			}
		}
		if (primarykey.length() != 0) {
			sql.append(",\nPRIMARY KEY (" + primarykey + ")");
		}
		sql.append("\n)");
		return sql.toString();
	}
	/**
	 * 插入语句
	 * @return
	 */
	public String getInsertSql(){
		if(null==tableName||"".equals(tableName))
			return null;
		StringBuffer insertSql = new StringBuffer("insert into "+getTableName());
		StringBuffer insertSqlValue = new StringBuffer();
		insertSql.append("\n (");
		insertSqlValue.append("values\n (");
		for (int i=0;i<columnEntitys.size();i++) {
			ColumnEntity columnEntity = columnEntitys.get(i);
			insertSql.append(columnEntity.getColumnName());
			if(columnEntity.getValue() instanceof Date){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				insertSqlValue.append("'"+dateFormat.format(columnEntity.getValue())+"'");
			}else{
				insertSqlValue.append("'"+columnEntity.getValue()+"'");
			}
			if (i != columnEntitys.size() - 1) {
				insertSql.append(",");
				insertSqlValue.append(",");
			}
		}
		// 拼接两部分的sql
		insertSql.append(")\n"+insertSqlValue+")");
		return insertSql.toString();
	}
	/**
	 * 查询语句
	 * @return
	 */
	public String getSelectSQL(){
		if(null==tableName||"".equals(tableName)||null==primarykey||"".equals(primarykey))
			return null;
		StringBuffer insertSql = new StringBuffer("select * from "+tableName+" where "+primarykey+"=");
		Object id = null;
		for (int i=0;i<columnEntitys.size();i++) {
			ColumnEntity columnEntity = columnEntitys.get(i);
			if(primarykey.equals(columnEntity.getColumnName())){
				id = columnEntity.getValue();
			}
		}
		if(id!=null){
			insertSql.append(id);
		}
		return insertSql.toString();
	}
	/**
	 * 更新语句
	 * @return
	 */
	public String getUpdateSql() {
		if(null==tableName||"".equals(tableName)||null==primarykey||"".equals(primarykey))
			return null;
		StringBuffer updateSql = new StringBuffer("update "+tableName+"\nset");
		Object id = null;
		for (int i=0;i<columnEntitys.size();i++) {
			ColumnEntity columnEntity = columnEntitys.get(i);
			if(primarykey.equals(columnEntity.getColumnName())){
				id = columnEntity.getValue();
				continue;
			}
			updateSql.append("  "+columnEntity.getColumnName()+"=");
			if(columnEntity.getValue() instanceof Date){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				updateSql.append("'"+dateFormat.format(columnEntity.getValue())+"'");
			}else{
				updateSql.append("'"+columnEntity.getValue()+"'");
			}
			if (i != columnEntitys.size() - 1) {
				updateSql.append(",\n");
			}
		}
		if(id==null)
			return null;
		updateSql.append("\nwhere ").append(primarykey).append("="+id);
		return updateSql.toString();
	}
	/**
	 * 删除语句
	 * @return
	 */
	public String getDeleteSql() {
		if(null==tableName||"".equals(tableName)||null==primarykey||"".equals(primarykey))
			return null;
		StringBuffer deleteSql = new StringBuffer("delete from "+tableName+"  where "+primarykey+"=");
		Object id = null;
		for (int i=0;i<columnEntitys.size();i++) {
			ColumnEntity columnEntity = columnEntitys.get(i);
			if(primarykey.equals(columnEntity.getColumnName())){
				id = columnEntity.getValue();
			}
		}
		if(id==null)
			return null;
		deleteSql.append(id);
		return deleteSql.toString();
	}
}
