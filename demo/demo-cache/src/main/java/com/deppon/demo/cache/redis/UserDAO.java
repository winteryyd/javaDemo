package com.deppon.demo.cache.redis;

public interface UserDAO {
	 public void saveUser(final User user);
	 public User getUser(final long id) ;
}
