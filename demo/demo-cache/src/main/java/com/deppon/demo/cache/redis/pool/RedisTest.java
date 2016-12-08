package com.deppon.demo.cache.redis.pool;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static Jedis jedis; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisTest rt = new RedisTest();
		RedisTest.jedis=RedisUtil.getJedis();
		rt.testString();
		RedisUtil.returnResource(jedis);
	}
	
	public void testString() {
        //-----添加数据----------  
        jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin  
        System.out.println(jedis.get("name"));//执行结果：xinxin  
        
        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liuling","age","23","qq","476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
        
    }
}
