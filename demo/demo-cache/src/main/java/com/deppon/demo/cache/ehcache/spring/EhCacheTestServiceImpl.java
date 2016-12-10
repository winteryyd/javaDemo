package com.deppon.demo.cache.ehcache.spring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {
	
	@Cacheable(value="cacheTest",key="#param")
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
		return timestamp.toString();
	}

}
