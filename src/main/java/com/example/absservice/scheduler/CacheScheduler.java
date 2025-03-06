package com.example.absservice.scheduler;

import java.time.*;

import lombok.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import com.example.absservice.service.*;

@Component
@RequiredArgsConstructor
public class CacheScheduler {

    private final CacheInvalidateService cacheInvalidateService;

    @Scheduled(cron = "${cache.scheduler.cron}")
    private void scheduleUpdateCache() {
        var currentTime = ZonedDateTime.now();
        cacheInvalidateService.flushFinancialProductsCache(currentTime);
        cacheInvalidateService.flushDictionariesCache(currentTime);
    }

}
