package com.deppon.demo.jdbc.entity;

public class ColumnEntity {
	private String property;
	private String columnName;
	private String type;
	private int length;
	private Object value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public String toString(){
		return "ColunmnEntity[columnName:"+columnName+"  type:"+type+"  length:"+length+"]";
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
