package com.example.absservice.aspect;

import java.time.*;
import java.time.format.*;

import lombok.*;
import lombok.extern.slf4j.*;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

import com.example.absservice.service.*;
import com.example.absservice.swagger.dto.*;

import static com.example.absservice.model.CacheKeys.*;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CachingAspect {

    private final CacheService cacheService;

    @Pointcut(
        "execution(* com.example.absservice.controller." +
        "FinancialProductsApiController.financialProductsGet())"
    )
    public void financialProductsGetPointcut() {
    }

    @Pointcut(
        "execution(* com.example.absservice.controller." +
        "DictionariesApiController.dictionariesGet())"
    )
    public void dictionariesGetPointcut() {
    }

    @Around("financialProductsGetPointcut()")
    public ResponseEntity<FinancialProductsResponseDto> financialProductsGetAdvice(
        ProceedingJoinPoint joinPoint
    ) {
        return getResponseCached(FINANCIAL_PRODUCTS_CACHE_NAME.getName(), joinPoint);
    }

    @Around("dictionariesGetPointcut()")
    public ResponseEntity<ParametersDto> dictionariesGetAdvice(
        ProceedingJoinPoint joinPoint
    ) {
        return getResponseCached(DICTIONARIES_CACHE_NAME.getName(), joinPoint);
    }

    @SneakyThrows
    private <T> ResponseEntity<T> getResponseCached(String key, ProceedingJoinPoint joinPoint) {
        ResponseEntity<T> responseFromCacheHeaders =
            getResponseFromCacheHeaders(key);

        if (responseFromCacheHeaders != null) {
            return responseFromCacheHeaders;
        }

        ResponseEntity<T> cachedResponse =
            getResponseFromCache(key);
        if (cachedResponse != null) {
            return cachedResponse;
        }

        ResponseEntity<T> response =
            (ResponseEntity<T>) joinPoint.proceed();

        updateCache(key, response.getBody());
        var etag = cacheService.getEtagFromCache(key);
        var lastModified = cacheService.getLastModifiedFromCache(key);
        return ResponseEntity.ok()
            .eTag(etag)
            .lastModified(lastModified)
            .body(response.getBody());
    }

    private <T> ResponseEntity<T> getResponseFromCache(String key) {
        T cache = cacheService.getFromCache(key);
        if (cache == null) {
            return null;
        }
        var etag = cacheService.getEtagFromCache(key);
        var lastModified = cacheService.getLastModifiedFromCache(
            key);
        return ResponseEntity.ok()
            .eTag(etag)
            .lastModified(lastModified)
            .body(cache);

    }

    private <T> ResponseEntity<T> getResponseFromCacheHeaders(String key) {
        var requestAttributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var servletRequest = requestAttributes.getRequest();
        var requestEtag = servletRequest.getHeader("If-None-Match");
        var requestLastModified = servletRequest.getHeader("If-Modified-Since");

        String etag = cacheService.getEtagFromCache(key);
        ZonedDateTime lastModified = cacheService.getLastModifiedFromCache(key);

        if (etag != null && etag.equals(requestEtag)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        if (lastModified != null && requestLastModified != null) {
            return getResponseFromLastModifiedHeader(lastModified, requestLastModified);
        }
        return null;
    }

    private <T> ResponseEntity<T> getResponseFromLastModifiedHeader(
        ZonedDateTime lastModified,
        String requestLastModifiedHeader
    ) {
        try {
            var requestLastModified = ZonedDateTime.parse(
                requestLastModifiedHeader,
                DateTimeFormatter.RFC_1123_DATE_TIME);
            if (lastModified.isBefore(requestLastModified)) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
        } catch (RuntimeException e) {
            log.warn("Failed to parse date", e);
        }
        return null;
    }

    private void updateCache(String key, Object value) {
        var lastUpdate = getAndPutIfNullLastUpdate(key);
        var etag = cacheService.calculateEtag(value);
        cacheService.putEtagInCache(key, etag);
        cacheService.putLastModifiedInCache(key, lastUpdate);
        cacheService.putInCache(key, value);
    }

    private ZonedDateTime getAndPutIfNullLastUpdate(String key) {
        var lastUpdate = cacheService.getLastUpdateFromCache(key);
        if (lastUpdate != null) {
            return lastUpdate;
        }
        lastUpdate = ZonedDateTime.now();
        cacheService.putLastUpdateInCache(key, lastUpdate);
        return lastUpdate;
    }

}
