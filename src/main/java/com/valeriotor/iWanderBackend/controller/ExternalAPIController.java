package com.valeriotor.iWanderBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/external")
public class ExternalAPIController {

    private final Environment environment;
    private final JedisPool jedisPool;

    @Autowired
    public ExternalAPIController(Environment environment, JedisPool jedisPool) {
        this.environment = environment;
        this.jedisPool = jedisPool;
    }

    @GetMapping("/geodb/cities")
    public String findCitiesByPrefix(@RequestParam Map<String, String> params) throws IOException, InterruptedException {
        String apiKey = environment.getProperty("api.key.geodb");
        String str = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities" + mapToURLParameters(params);

        Optional<String> cached = checkCache(str);
        if(cached.isPresent()) return cached.get();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(str))
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "wft-geo-db.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        addToCache(str, response.body(), 86400);

        return response.body();
    }

    @GetMapping("/places/radius")
    public String findPlacesInRadius(@RequestParam Map<String, String> params) throws IOException, InterruptedException {
        String apiKey = environment.getProperty("api.key.places");
        params.put("apikey", apiKey);
        String str = "https://api.opentripmap.com/0.1/en/places/radius" + mapToURLParameters(params);

        Optional<String> cached = checkCache(str);
        if(cached.isPresent()) return cached.get();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(str))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        addToCache(str, response.body(), 86400);

        return response.body();
    }

    @GetMapping("/places/xid/{xid}")
    public String findPlaceByXid(@PathVariable String xid, @RequestParam Map<String, String> params) throws IOException, InterruptedException {
        String apiKey = environment.getProperty("api.key.places");
        params.put("apikey", apiKey);
        String str = "https://api.opentripmap.com/0.1/en/places/xid/" + xid + mapToURLParameters(params);

        Optional<String> cached = checkCache(str);
        if(cached.isPresent()) return cached.get();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(str))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        addToCache(str, response.body(), 86400);

        return response.body();

    }

    private String mapToURLParameters(Map<String, String> map) {
        String pathParameters = map.entrySet()
                .stream()
                .map(e -> e.getKey()+"="+e.getValue())
                .collect(Collectors.joining("&"));
        return map.isEmpty() ? pathParameters : "?" + pathParameters;
    }

    private Optional<String> checkCache(String key) {
        Jedis jedis = jedisPool.getResource();
        String cached = jedis.get(key);
        System.out.println(cached);
        return cached == null ? Optional.empty() : Optional.of(cached);
    }

    private void addToCache(String key, String value, int secondsToExpire) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value, SetParams.setParams().ex(secondsToExpire));
    }

}
