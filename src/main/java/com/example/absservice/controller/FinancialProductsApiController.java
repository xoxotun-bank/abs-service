package com.example.absservice.controller;

import lombok.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.example.absservice.service.*;
import com.example.absservice.swagger.api.*;
import com.example.absservice.swagger.dto.*;

@RestController
@RequestMapping("api/v2/abs/")
@RequiredArgsConstructor
public class FinancialProductsApiController implements FinancialProductsApi {

    private final FinancialProductsService financialProductsService;

    private final ModelMapper mapper;

    @PreAuthorize("hasRole('operator')")
    @Override
    public ResponseEntity<FinancialProductsResponseDto> financialProductsGet() {
        var financialProducts = financialProductsService.getFinancialProducts();

        var financialProductDtos = financialProducts.stream()
            .map(financialProduct -> mapper.map(
                financialProduct,
                AbsFinancialProductDto.class))
            .toList();

        var responseDto = new FinancialProductsResponseDto();
        responseDto.setProducts(financialProductDtos);

        return ResponseEntity.ok(responseDto);
    }

}
