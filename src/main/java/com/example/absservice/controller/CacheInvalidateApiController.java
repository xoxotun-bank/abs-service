package com.example.absservice.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.example.absservice.service.*;
import com.example.absservice.swagger.api.*;
import com.example.absservice.swagger.dto.*;

@RestController
@RequestMapping("api/v2/abs/")
@RequiredArgsConstructor
public class CacheInvalidateApiController implements CacheInvalidateApi {

    private final CacheInvalidateService cacheInvalidateService;

    @Override
    @PreAuthorize("hasRole('administrator')")
    public ResponseEntity<Void> cacheInvalidatePost(CacheinvalidateBody body) {
        cacheInvalidateService.flushDictionariesCache(body.getDate().toZonedDateTime());
        cacheInvalidateService.flushFinancialProductsCache(body.getDate().toZonedDateTime());

        return ResponseEntity.ok().build();
    }

}
