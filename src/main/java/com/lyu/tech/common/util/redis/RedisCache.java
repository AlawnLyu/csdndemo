package com.lyu.tech.common.util.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lyu.tech.common.util.json.JsonHelper;
import com.lyu.tech.common.util.serialize.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisCache {

  @Autowired private RedisTemplate<String, ?> redisTemplate;

  public boolean setString(String key, String value) {
    boolean result =
        redisTemplate.execute(
            new RedisCallback<Boolean>() {
              @Override
              public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                redisConnection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
              }
            });
    return result;
  }

  public String getString(String key) {
    String result =
        redisTemplate.execute(
            new RedisCallback<String>() {
              @Override
              public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = redisConnection.get(serializer.serialize(key));
                return serializer.deserialize(value);
              }
            });
    return result;
  }

  public boolean expire(String key, long expire) {
    return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
  }

  public <T> boolean setList(String key, List<T> list) throws IOException {
    String value = JsonHelper.writeObject(list);
    return setString(key, value);
  }

  public <T> List<T> getList(String key, Class<T> clz) throws IOException {
    String json = getString(key);
    if (json != null) {
      List<T> list = JsonHelper.readObject(json, new TypeReference<List<T>>() {});
      return list;
    }
    return null;
  }

  /**
   * 从redis中获取数据
   *
   * @param key
   * @param elementType
   * @param <T>
   * @return
   */
  public <T> T getObject(final String key, Class<T> elementType) {
    return redisTemplate.execute(
        (RedisCallback<T>)
            redisConnection -> {
              byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
              if (redisConnection.exists(keybytes)) {
                byte[] valuebytes = redisConnection.get(keybytes);
                @SuppressWarnings("unchecked")
                T value = (T) SerializeUtil.unserialize(valuebytes);
                return value;
              }
              return null;
            });
  }

  /**
   * 设值到redis中
   *
   * @param key
   * @param obj
   */
  public void setObject(String key, Object obj) {
    final byte[] bytes = SerializeUtil.serialize(obj);
    redisTemplate.execute(
        (RedisCallback<Object>)
            redisConnection -> {
              redisConnection.set(redisTemplate.getStringSerializer().serialize(key), bytes);
              return null;
            });
  }

  public long rPush(String key, Object obj) throws IOException {
    String value = JsonHelper.writeObject(obj);
    long result =
        redisTemplate.execute(
            (RedisCallback<Long>)
                redisConnection -> {
                  RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                  long count =
                      redisConnection.rPush(serializer.serialize(key), serializer.serialize(value));
                  return count;
                });
    return result;
  }

  public String lPop(String key) {
    return redisTemplate.execute(
        (RedisCallback<String>)
            redisConnection -> {
              RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
              byte[] res = redisConnection.lPop(serializer.serialize(key));
              return serializer.deserialize(res);
            });
  }
}
