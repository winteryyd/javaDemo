package com.deppon.demo.jdbc.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TableEntity {
	private String primarykey;
	private String tableName;
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
		if (columnEntitys.size() == 0) {
			return null;
		}
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
	
	public String getInsertSql(){
		StringBuffer insertSql = new StringBuffer("insert into "+getTableName());
		StringBuffer insertSqlValue = new StringBuffer();
		insertSql.append(" (");
		insertSqlValue.append(" values(");
		for (ColumnEntity columnEntity : columnEntitys) {
			insertSql.append(columnEntity.getColumnName()+",");
			if(columnEntity.getValue() instanceof Date){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				insertSqlValue.append("'"+dateFormat.format(columnEntity.getValue())+"',");
			}else{
				insertSqlValue.append("'"+columnEntity.getValue()+"',");
			}
		}
		insertSql.replace(insertSql.lastIndexOf(","), insertSql.length(), ")");
		insertSqlValue.replace(insertSqlValue.lastIndexOf(","), insertSqlValue
				.length(), ")");
		// 拼接两部分的sql
		insertSql.append(insertSqlValue);
		return insertSql.toString();
	}
}
