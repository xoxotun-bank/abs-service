package com.example.absservice.service;

import java.security.*;
import java.time.*;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.security.crypto.codec.*;
import org.springframework.stereotype.*;

import static com.example.absservice.model.CacheKeys.*;

@Service
@RequiredArgsConstructor
public class RedisCacheService implements CacheService {

    private final RedisTemplate<Object, Object> redisTemplate;

    private static final MessageDigest DIGEST;

    static {
        var algorithm = "SHA3-256";
        try {
            DIGEST = MessageDigest.getInstance(algorithm);
        } catch (Exception ex) {
            throw new RuntimeException(String.format(
                "Can't create digest: %s", algorithm
            ), ex);
        }
    }

    @Value("${cache.ttl}")
    private int cacheDefaultTtlSeconds;

    @Override
    public <T> T getFromCache(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public ZonedDateTime getLastUpdateFromCache(String name) {
        var key = LAST_UPDATE_PREFIX.getName() + name;
        return getFromCache(key);
    }

    @Override
    public String getEtagFromCache(String name) {
        var key = ETAG_PREFIX.getName() + name;
        return getFromCache(key);
    }

    @Override
    public ZonedDateTime getLastModifiedFromCache(String key) {
        return getFromCache(LAST_MODIFIED_PREFIX.getName() + key);
    }

    @Override
    public void putInCache(String key, Object value) {
        ZonedDateTime lastUpdate = getLastUpdateFromCache(key);
        var duration = calculateTtl(lastUpdate);
        redisTemplate.opsForValue().set(key, value, duration);
    }

    @Override
    public void putLastUpdateInCache(String name, ZonedDateTime lastUpdate) {
        var ttl = calculateTtl(lastUpdate);
        var key = LAST_UPDATE_PREFIX.getName() + name;
        redisTemplate.opsForValue().set(key, lastUpdate, ttl);
    }

    @Override
    public void flushCache(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    public void flushEtag(String name) {
        var key = ETAG_PREFIX.getName() + name;
        flushCache(key);
    }

    @Override
    public void flushLastModified(String name) {
        var key = LAST_MODIFIED_PREFIX.getName() + name;
        flushCache(key);
    }

    @Override
    public void putLastModifiedInCache(String name, ZonedDateTime lastModified) {
        var key = LAST_MODIFIED_PREFIX.getName() + name;
        var ttl = Duration.ofSeconds(cacheDefaultTtlSeconds);
        redisTemplate.opsForValue()
            .set(key, lastModified, ttl);
    }

    @Override
    public void putEtagInCache(String name, String etag) {
        var key = ETAG_PREFIX.getName() + name;
        var ttl = Duration.ofSeconds(cacheDefaultTtlSeconds);
        redisTemplate.opsForValue().set(key, etag, ttl);
    }

    private Duration calculateTtl(ZonedDateTime lastUpdate) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        int currentTimeInSeconds = currentTime.toLocalTime().toSecondOfDay();
        int lastUpdateInSeconds = lastUpdate.toLocalTime().toSecondOfDay();
        int cacheTtlSeconds =
            cacheDefaultTtlSeconds - (currentTimeInSeconds - lastUpdateInSeconds)
                                     % cacheDefaultTtlSeconds;
        return Duration.ofSeconds(cacheTtlSeconds);
    }

    @Override
    @SneakyThrows
    public String calculateEtag(Object value) {
        var valueBytes = value.toString().getBytes();
        var digest = DIGEST.digest(valueBytes);
        var hashBytes = Hex.encode(digest);
        var result = '"' + String.valueOf(hashBytes) + '"';
        return result;
    }

}
