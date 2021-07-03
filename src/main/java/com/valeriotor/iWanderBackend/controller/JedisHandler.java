package com.valeriotor.iWanderBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JedisHandler {

    private final JedisPool jedisPool;

    @Autowired
    public JedisHandler(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Optional<String> checkCache(String key) {
        String cached;
        try (Jedis jedis = jedisPool.getResource()) {
            cached = jedis.get(key);
        }
        return cached == null ? Optional.empty() : Optional.of(cached);
    }

    public void addToCache(String key, String value, int secondsToExpire) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value, SetParams.setParams().ex(secondsToExpire));
        }
    }

    public Optional<byte[]> checkCacheBytes(String key) {
        byte[] cached;
        try (Jedis jedis = jedisPool.getResource()) {
            cached = jedis.get(key.getBytes());
        }
        return cached == null ? Optional.empty() : Optional.of(cached);
    }

    public void addToCacheBytes(String key, byte[] value, int secondsToExpire) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key.getBytes(), value, SetParams.setParams().ex(secondsToExpire));
        }
    }

}
