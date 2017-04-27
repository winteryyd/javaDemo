package com.deppon.demo.cache.redis;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.deppon.demo.cache.ExpireCallback;
import com.deppon.demo.cache.ItemCallback;
import com.deppon.demo.cache.StringCache;
import com.deppon.demo.cache.Tuple;

@Component
public class RedisStringCache extends StringCache<Object> {

	@Autowired
	protected RedisTemplate<String, Object> jedisTemplate;

	public RedisTemplate<String, Object> getJedisTemplate() {
		return jedisTemplate;
	}

	public void setJedisTemplate(RedisTemplate<String, Object> jedisTemplate) {
		this.jedisTemplate = jedisTemplate;
	}

	public void set(String key, Object value) {
		this.jedisTemplate.opsForValue().set(key, value);
	}

	public void setEx(String key, Object value, long expire) {
		this.jedisTemplate.opsForValue().set(key, value, expire,
				TimeUnit.SECONDS);
	}

	public void mSet(Map<String, Object> values) {
		this.jedisTemplate.opsForValue().multiSet(values);
	}

	public void mSetWithPipeLine(final Map<String, Object> values,
			final ItemCallback<String, Object> itemCallback,
			final ExpireCallback<String, Object> expireCallback) {
		this.jedisTemplate.executePipelined(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				for (Map.Entry<String, Object> entry : values.entrySet()) {
					String key = entry.getKey();
					Object value = entry.getValue();
					if (itemCallback != null) {
						Tuple<String, Object> tuple = itemCallback.processItem(
								key, value);
						key = tuple.getKey();
						value = tuple.getValue();
					}

					byte[] rawKey = serialize(key,
							jedisTemplate.getKeySerializer());
					byte[] rawValue = serialize(value,
							jedisTemplate.getValueSerializer());

					if (expireCallback != null) {
						long expire = expireCallback.expire(key, value);
						if (expire <= 0) {
							connection.set(rawKey, rawValue);
						} else {
							connection.setEx(rawKey, expire, rawValue);
						}
					} else {
						connection.set(rawKey, rawValue);
					}
				}
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	private byte[] serialize(Object value, RedisSerializer serializer) {
		if (serializer == null && value instanceof byte[]) {
			return (byte[]) value;
		}
		if (serializer != null)
			return serializer.serialize(value);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) this.jedisTemplate.opsForValue().get(key);
	}

	public void del(Set<String> keys) {
		this.jedisTemplate.delete(keys);
	}

	public void del(String key) {
		this.jedisTemplate.delete(key);
	}

	public Set<String> keys(String pattern) {
		return this.jedisTemplate.keys(pattern);
	}

	public boolean exists(String key) {
		return this.jedisTemplate.hasKey(key);
	}

	public void multiDel(String pattern) {
		del(keys(pattern));
	}

	public long size(String key) {
		return ((String) get(key)).getBytes(charset).length;
	}

	public <T> void leftPush(String key, T value) {
		jedisTemplate.opsForList().leftPush(key, value);
	}

	public long listSize(final String key) {
		return jedisTemplate.opsForList().size(key);
	}

	public long increment(String key, long delta) {
		return jedisTemplate.opsForValue().increment(key, delta);
	}

	@SuppressWarnings("unchecked")
	public <T> T rightPop(String key) {
		return (T) jedisTemplate.opsForList().rightPop(key);
	}

	public void flushDB() {
		jedisTemplate.execute(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
	}

	public void flushAll() {
		jedisTemplate.execute(new RedisCallback<Object>() {

			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushAll();
				return null;
			}
		});
	}

	public Properties info() {
		return jedisTemplate.execute(new RedisCallback<Properties>() {

			public Properties doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.info();
			}
		});
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

}
