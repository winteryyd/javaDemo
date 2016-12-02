package com.deppon.demo.transaction;

public interface BankService {
    public void transfer(int fromId, int toId, int amount);
}
