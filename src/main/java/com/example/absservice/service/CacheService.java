package com.example.absservice.service;

import java.time.*;

public interface CacheService {

    <T> T getFromCache(String key);

    ZonedDateTime getLastUpdateFromCache(String name);

    String getEtagFromCache(String name);

    ZonedDateTime getLastModifiedFromCache(String key);

    void putInCache(String key, Object value);

    void putLastUpdateInCache(String name, ZonedDateTime lastUpdate);

    void flushCache(String key);

    void flushEtag(String name);

    void flushLastModified(String name);

    void putLastModifiedInCache(String name, ZonedDateTime lastModified);

    void putEtagInCache(String name, String etag);

    String calculateEtag(Object value);

}
