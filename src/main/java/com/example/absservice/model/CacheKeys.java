package com.example.absservice.model;

public enum CacheKeys {
    LAST_UPDATE_PREFIX("lastUpdate::"),
    LAST_MODIFIED_PREFIX("lastModified::"),
    ETAG_PREFIX("etag::"),
    DICTIONARIES_CACHE_NAME("dictionariesCache"),
    FINANCIAL_PRODUCTS_CACHE_NAME("financialProductsCache");

    private final String name;

    CacheKeys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
