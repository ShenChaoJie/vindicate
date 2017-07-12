package org.frame.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

public class RedisCache<K, V> implements Cache<K, V> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RedisManager cache;

	private String keyPrefix = "shiro_session:";

	public RedisCache(RedisManager cache) {
		if (cache == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.setCache(cache);
	}

	public RedisCache(RedisManager cache, String prefix) {
		this(cache);
		// set the prefix
		this.keyPrefix = prefix;
	}

	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		try {
			getCache().flushDB();
		} catch (Throwable t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
				return null;
			} else {
				byte[] rawValue = getCache().get(getByteKey(key));
				@SuppressWarnings("unchecked")
				V value = (V) SerializationUtils.deserialize(rawValue);
				return value;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = this.keyPrefix + key;
			return preKey.getBytes();
		} else if (key instanceof PrincipalCollection) {
			String preKey = this.keyPrefix + key.toString();
			return preKey.getBytes();
		} else {
			return SerializationUtils.serialize(key);
		}
	}

	@Override
	public Set<K> keys() {
		try {
			Set<byte[]> keys = getCache().keys(this.keyPrefix + "*");
			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (byte[] key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		try {
			getCache().set(getByteKey(key), SerializationUtils.serialize(value));
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
			V previous = get(key);
			getCache().del(getByteKey(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(getCache().dbSize());
			return longSize.intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			Set<byte[]> keys = getCache().keys(this.keyPrefix + "*");
			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (byte[] key : keys) {
					@SuppressWarnings("unchecked")
					V value = get((K) key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public RedisManager getCache() {
		return cache;
	}

	public void setCache(RedisManager cache) {
		this.cache = cache;
	}

}
