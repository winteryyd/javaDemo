package com.deppon.demo.mybatis.base;

import com.deppon.demo.cache.annotation.CacheDelete;
import com.deppon.demo.cache.annotation.CacheSave;
import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.framework.dao.ICrudDao;
import com.deppon.demo.mybatis.base.MbiBaseDao;

public abstract class CrudDao<T> extends MbiBaseDao implements ICrudDao<T> {

	public abstract String getNamespace();
	
	@Cacheable(
			namespace = "demo", 
			fieldsKey = { "#entityClass.name", "#id" }, 
			expire = 86400
			)
	public T get(Class<T> entityClass, String id) {
		return getSqlSession().selectOne(
                buildStatement(getNamespace(), "get"),
                getParamsBuilder()
                        .put("id", id).build());
	}

	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public int insert(T entity) {
		return getSqlSession().insert(buildStatement(getNamespace(), "insert"), entity);
	}

	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public int update(T entity) {
		return getSqlSession().update(buildStatement(getNamespace(), "update"),entity);
	}

	@CacheDelete(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}
			)
	public int delete(T entity) {
		return getSqlSession().delete(buildStatement(getNamespace(), "delete"),entity);
	}


	public void destroy() throws Exception {
		
	}

}
