package com.valeriotor.iWanderBackend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JedisTests {

    //@Test
    public void testJedisString() {
        Jedis jedis = new Jedis();
        jedis.set("/findUser?prefix=va", "valeriotor");
        String cachedResponse = jedis.get("/findUser?prefix=va");
        String nonCachedRespone = jedis.get("kawofkoawkfowkafkwoafkwa");
        assertEquals(cachedResponse, "valeriotor");
        assertNull(nonCachedRespone);
    }

}
