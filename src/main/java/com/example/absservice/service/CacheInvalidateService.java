package com.example.absservice.service;

import java.time.*;

import lombok.*;
import org.springframework.stereotype.*;

import static com.example.absservice.model.CacheKeys.*;

@Service
@RequiredArgsConstructor
public class CacheInvalidateService {

    private final CacheService cacheService;

    public void flushDictionariesCache(ZonedDateTime lastUpdate) {
        cacheService.flushCache(DICTIONARIES_CACHE_NAME.getName());
        cacheService.flushEtag(DICTIONARIES_CACHE_NAME.getName());
        cacheService.flushLastModified(DICTIONARIES_CACHE_NAME.getName());
        cacheService.putLastUpdateInCache(DICTIONARIES_CACHE_NAME.getName(), lastUpdate);
    }

    public void flushFinancialProductsCache(ZonedDateTime lastUpdate) {
        cacheService.flushCache(FINANCIAL_PRODUCTS_CACHE_NAME.getName());
        cacheService.flushEtag(FINANCIAL_PRODUCTS_CACHE_NAME.getName());
        cacheService.flushLastModified(FINANCIAL_PRODUCTS_CACHE_NAME.getName());
        cacheService.putLastUpdateInCache(FINANCIAL_PRODUCTS_CACHE_NAME.getName(), lastUpdate);
    }

}
