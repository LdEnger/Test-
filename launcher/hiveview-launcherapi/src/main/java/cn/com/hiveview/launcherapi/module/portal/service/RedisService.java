package cn.com.hiveview.launcherapi.module.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Set;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private ShardedJedisPool shardedJedisPool;


//	@PostConstruct
//	public void init(){
//		KEYS=new Keys();
//		STRINGS=new Strings();
//		LISTS=new Lists();
//		SETS=new Sets();
//		SORTSET=new SortSet();
//		HASH=new Hash();
//		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% > " + this.KEYS.exists("xxx"));
//	}

    public String flushAll() {
        Jedis jedis = jedisPool.getResource();
        String stata = "";
        try {
            stata = jedis.flushAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//				jedisPool.returnResource(jedis);
            jedis.close();
        }
        return stata;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    public String rename(String oldkey, String newkey) {
        return rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
    }

    /**
     * 更改key,仅当新key不存在时才执行
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    public long renamenx(String oldkey, String newkey) {
        Jedis jedis = jedisPool.getResource();
        long status = 0;
        try {
            status = jedis.renamenx(oldkey, newkey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return status;
    }

    /**
     * 更改key
     *
     * @param oldkey
     * @param newkey
     * @return 状态码
     */
    public String rename(byte[] oldkey, byte[] newkey) {
        Jedis jedis = jedisPool.getResource();
        String status = "";
        try {
            status = jedis.rename(oldkey, newkey);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return status;
    }

    /**
     * 设置key的过期时间，以秒为单位
     *
     * @param key
     * @param seconds,已秒为单位
     * @return 影响的记录数
     */
    public long expired(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
     *
     * @param key
     * @param timestamp,已秒为单位
     * @return 影响的记录数
     */
    public long expireAt(String key, long timestamp) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.expireAt(key, timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 查询key的过期时间
     *
     * @param key
     * @return 以秒为单位的时间表示
     */
    public long ttl(String key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        long len = 0;
        try {
            len = sjedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return len;
    }

    /**
     * 取消对key过期时间的设置
     *
     * @param key
     * @return 影响的记录数
     */
    public long persist(String key) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.persist(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 删除keys对应的记录,可以是多个key
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(String... keys) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//				jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 删除keys对应的记录,可以是多个key
     *
     * @param keys
     * @return 删除的记录数
     */
    public long del(byte[]... keys) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return boolean
     */
    public boolean exists(String key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        boolean exis = false;
        try {
            exis = sjedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return exis;
    }

    /**
     * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法
     *
     * @param key
     * @return List<String> 集合的全部记录
     **/
    public List<String> sort(String key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        List<String> list = null;
        try {
            list = sjedis.sort(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return list;
    }

    /**
     * 对List,Set,SortSet进行排序或limit
     *
     * @param key
     * @param parame 定义排序类型或limit的起止位置.
     * @return List<String> 全部或部分记录
     **/
    public List<String> sort(String key, SortingParams parame) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        List<String> list = null;
        try {
            list = sjedis.sort(key, parame);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return list;
    }

    /**
     * 返回指定key存储的类型
     *
     * @param key
     * @return String  string|list|set|zset|hash
     **/
    public String type(String key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        String type = "";
        try {
            type = sjedis.type(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return type;
    }

    /**
     * 查找所有匹配给定的模式的键
     *
     * @param pattern 表达式,*表示多个，？表示一个
     */
    public Set<String> kyes(String pattern) {
        Jedis jedis = jedisPool.getResource();
        Set<String> set = null;
        try {
            set = jedis.keys(pattern);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return set;
    }

    /**
     * 根据传入的key进行 前缀匹配 找出所有key 并删除
     * @param firstPatten
     */
    public  boolean clearPrefex(String firstPatten) {
        Jedis jedis = jedisPool.getResource();
        Long parm=0L;
        try {
            Set<String> keys = jedis.keys(firstPatten + "*");
            for (String key : keys) {
                parm=jedis.del(key);
            }
            if(parm>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }finally {
            jedis.close();
        }
    }

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public String get(String key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        String value = "";
        try {
            value = sjedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return value;
    }

    /**
     * 根据key获取记录
     *
     * @param key
     * @return 值
     */
    public byte[] get(byte[] key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        byte[] value = null;
        try {
            value = sjedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return value;
    }

    /**
     * 添加有过期时间的记录
     *
     * @param key
     * @param seconds 过期时间，以秒为单位
     * @param value
     * @return String 操作状态
     */
    public String setEx(String key, int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = "";
        try {
            str = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }


    /**
     * 添加有过期时间的记录
     *
     * @param key
     * @param seconds 过期时间，以秒为单位
     * @param value
     * @return String 操作状态
     */
    public String setEx(byte[] key, int seconds, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String str = "";
        try {
            str = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 添加一条记录，仅当给定的key不存在时才插入
     *
     * @param key
     * @param value
     * @return long 状态码，1插入成功且key不存在，0未插入，key存在
     */
    public long setnx(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long str = 0;
        try {
            str = jedis.setnx(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(String key, String value) {
        return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value
     *
     * @param key
     * @param value
     * @return 状态码
     */
    public String set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String status = "";
        try {
            status = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return status;
    }

    /**
     * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
     * 例:String str1="123456789";<br/>
     * 对str1操作后setRange(key,4,0000)，str1="123400009";
     *
     * @param key
     * @param offset
     * @param value
     * @return long value的长度
     */
    public long setRange(String key, long offset, String value) {
        Jedis jedis = jedisPool.getResource();
        long len = 0;
        try {
            len = jedis.setrange(key, offset, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return len;
    }

    /**
     * 在指定的key中追加value
     *
     * @param key
     * @param value
     * @return long 追加后value的长度
     **/
    public long append(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long len = 0;
        try {
            len = jedis.append(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return len;
    }

    /**
     * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @param number 要减去的值
     * @return long 减指定值后的值
     */
    public long decrBy(String key, long number) {
        Jedis jedis = jedisPool.getResource();
        long len = 0;
        try {
            len = jedis.decrBy(key, number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return len;
    }

    /**
     * <b>可以作为获取唯一id的方法</b><br/>
     * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key
     * @param number 要减去的值
     * @return long 相加后的值
     */
    public long incrBy(String key, long number) {
        Jedis jedis = jedisPool.getResource();
        long len = 0;
        try {
            len = jedis.incrBy(key, number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return len;
    }

    /**
     * 对指定key对应的value进行截取
     *
     * @param key
     * @param startOffset 开始位置(包含)
     * @param endOffset   结束位置(包含)
     * @return String 截取的值
     */
    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        String value = "";
        try {
            value = sjedis.getrange(key, startOffset, endOffset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return value;
    }

    /**
     * 获取并设置指定key对应的value<br/>
     * 如果key存在返回之前的value,否则返回null
     *
     * @param key
     * @param value
     * @return String 原始value或null
     */
    public String getSet(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = "";
        try {
            str = jedis.getSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 批量获取记录,如果指定的key不存在返回List的对应位置将是null
     *
     * @param keys
     * @return List<String> 值得集合
     */
    public List<String> mget(String... keys) {
        Jedis jedis = jedisPool.getResource();
        List<String> str = null;
        try {
            str = jedis.mget(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 批量存储记录
     *
     * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
     * @return String 状态码
     */
    public String mset(String... keysvalues) {
        Jedis jedis = jedisPool.getResource();
        String str = "";
        try {
            str = jedis.mset(keysvalues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 获取key对应的值的长度
     *
     * @param key
     * @return value值得长度
     */
    public long strlen(String key) {
        Jedis jedis = jedisPool.getResource();
        long len = 0;
        try {
            len = jedis.strlen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return len;
    }

    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(String key) {
        return llen(SafeEncoder.encode(key));
    }

    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(byte[] key) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        long count = 0;
        try {
            count = sjedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return count;
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(byte[] key, int index, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        String status = "";
        try {
            status = jedis.lset(key, index, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return status;
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(String key, int index, String value) {
        return lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public String lindex(String key, int index) {
        return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public byte[] lindex(byte[] key, int index) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        byte[] value = null;
        try {
            value = sjedis.lindex(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return value;
    }

    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String lpop(String key) {
        byte[] value = lpop(SafeEncoder.encode(key));
        return SafeEncoder.encode(value == null ? "".getBytes() : value);
    }

    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public byte[] lpop(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        byte[] value = null;
        try {
            value = jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return value;
    }

    /**
     * 将List中最后第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String rpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = "";
        try {
            value = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return value;
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(String key, String value) {
        return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 向List尾部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 向List尾部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 向List中追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end
     * @return List
     */
    public List<String> lrange(String key, long start, long end) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        List<String> list = null;
        try {
            list = sjedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return list;
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end   如果为负数，则尾部开始计算
     * @return List
     */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        ShardedJedis sjedis = shardedJedisPool.getResource();
        List<byte[]> list = null;
        try {
            list = sjedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //shardedJedisPool.returnResource(sjedis);
            sjedis.close();
        }
        return list;
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(byte[] key, int c, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        long count = 0;
        try {
            count = jedis.lrem(key, c, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return count;
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(String key, int c, String value) {
        return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    public String ltrim(byte[] key, int start, int end) {
        Jedis jedis = jedisPool.getResource();
        String str = "";
        try {
            str = jedis.ltrim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //jedisPool.returnResource(jedis);
            jedis.close();
        }
        return str;
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return
     */
    public String ltrim(String key, int start, int end) {
        return ltrim(SafeEncoder.encode(key), start, end);
    }
}