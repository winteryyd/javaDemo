package com.deppon.demo.mybatis.base;

import org.apache.ibatis.session.SqlSessionFactory;

import com.deppon.demo.base.dao.BaseDao;

import javax.annotation.Resource;

/**
 * Created by 264929 on 2016/1/20.
 *
 * Base DataSource for MBI
 */
public abstract class MbiBaseDao extends BaseDao {

    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
