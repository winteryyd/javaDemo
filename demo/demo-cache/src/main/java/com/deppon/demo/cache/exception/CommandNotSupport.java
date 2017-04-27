package com.deppon.demo.cache.exception;

public class CommandNotSupport extends RuntimeException {
    public CommandNotSupport(String cmd) {
        super(cmd + " not support");
    }
}
