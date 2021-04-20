package com.valeriotor.iWanderBackend.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
public class TestProperties {

    private final Environment environment;

    @Autowired
    public TestProperties(Environment environment) {
        this.environment = environment;
    }

    @Test
    public void testGetApiKeyProperty() {
        String key0 = environment.getProperty("api.key.geodb");
        String key1 = environment.getProperty("api.key.places");

        assert key0 != null;
        assert key0.endsWith("a");
        assert key1 != null;
        assert key1.endsWith("0");
    }

}
