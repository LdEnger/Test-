package cn.com.hiveview.core.cache;

import cn.com.hiveview.core.util.SerializeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public class RedisCache<K, V> implements Cache<K, V> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The wrapped Jedis instance.
     */
    private ShardedJedis cache;

    /**
     * The Redis key prefix for the sessions
     */
    @Setter
    @Getter
    private String keyPrefix = "shiro_redis_session:";

    /**
     * 通过一个JedisManager实例构造RedisCache
     */
    public RedisCache(ShardedJedis cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    /**
     * Constructs a cache instance with the specified
     * Redis manager and using a custom key prefix.
     *
     * @param cache  The cache manager instance
     * @param prefix The Redis key prefix
     */
    public RedisCache(ShardedJedis cache, String prefix) {
        this(cache);
        this.keyPrefix = prefix;
    }

    /**
     * 获得byte[]型的key
     *
     * @param key
     * @return
     */
    private byte[] getByteKey(K key) {
        if (key instanceof String) {
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        } else {
            return SerializeUtil.serialize(key);
        }
    }

    @Override
    public V get(K key) {
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        if (key == null) {
            return null;
        } else {
            byte[] rawValue = cache.get(getByteKey(key));
            if (rawValue != null) {
                V value = (V) SerializeUtil.unserialize(rawValue);
                return value;
            } else {
                return null;
            }
        }

    }

    @Override
    public V put(K key, V value) {
        logger.debug("根据key从存储 key [" + key + "]");
        cache.set(getByteKey(key), SerializeUtil.serialize(value));
        return value;
    }

    @Override
    public V remove(K key) {
        logger.debug("从redis中删除 key [" + key + "]");
        V previous = get((K) (this.keyPrefix + key));
        cache.del(getByteKey(key));
        return previous;
    }

    @Override
    public void clear() {
        logger.debug("从redis中删除所有元素");
        Set<String> keys = cache.hkeys(this.keyPrefix + "*");
        for (String key : keys) {
            cache.del(key);
        }
    }

    @Override
    public int size() {
        Long longSize = new Long(cache.hkeys(keyPrefix).size());
        return longSize.intValue();
    }

    @Override
    public Set<K> keys() {
        Set<K> newKeys = Sets.newHashSet();
        Set<String> keys = cache.hkeys(this.keyPrefix + "*");
        for (String key : keys) {
            newKeys.add((K) key);
        }
        return newKeys;
    }

    @Override
    public Collection<V> values() {
        Set<String> keys = cache.hkeys(this.keyPrefix + "*");
        List<V> values = Lists.newArrayList();
        for (String key : keys) {
            V value = get((K) key);
            values.add(value);
        }
        return values;
    }

}
