package com.deppon.demo.base.dao;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 264929 on 2016/1/20.
 *
 * Base Class for DataSource Access.
 */
public abstract class BaseDao extends SqlSessionDaoSupport {

    /**
     * Concatenate mybatis mapper namespace and execute sql id.
     * This is a convenient method.
     */
    protected String buildStatement(String namespace, String id) {
        String namespaceVar = namespace;
        if (!StringUtils.isEmpty(namespace) && namespace.endsWith(".")) {
            namespaceVar = namespaceVar.substring(0, namespaceVar.length() - 1);
        }
        return namespaceVar + "." + id;
    }

    /**
     * Convenient method for mybatis used with map params
     */
    protected ParamsBuilder getParamsBuilder() {
        return new ParamsBuilder();
    }

    protected class ParamsBuilder {
        // ConcurrentHashMap does not allow null to be used as a key or value.
        Map<String, Object> map = new HashMap<String, Object>();

        private ParamsBuilder() {
        }

        public ParamsBuilder put(String key, Object value) {
            this.map.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return this.map;
        }
    }
}
