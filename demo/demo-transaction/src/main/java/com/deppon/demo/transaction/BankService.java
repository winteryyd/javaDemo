package com.deppon.demo.transaction;

import javax.sql.DataSource;

public interface BankService {
	public abstract void init(DataSource dataSource);
    public void transfer(int fromId, int toId, int amount);
}
