package com.example.absservice.service;

import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.absservice.exception.*;
import com.example.absservice.model.*;

@Service
@RequiredArgsConstructor
public class DictionariesService {

    private final FinancialProductsService financialProductsService;

    public Dictionaries getDictionaries() {
        List<FinancialProductEntity> financialProducts =
            financialProductsService.getFinancialProducts();

        if (financialProducts.isEmpty()) {
            throw new DictionariesNotFoundException();
        }

        return parseFinancialProducts(financialProducts);
    }

    private Dictionaries parseFinancialProducts(List<FinancialProductEntity> financialProducts) {
        Set<CapitalizationPeriodEntity> capitalizationPeriods = new HashSet<>();
        Set<PeriodEntity> periods = new TreeSet<>(Comparator.comparing(PeriodEntity::getValue));
        Set<CurrencyEntity> currencies = new HashSet<>();

        financialProducts
            .forEach(product -> {
                capitalizationPeriods.add(product.getCapitalizationPeriod());
                periods.add(product.getPeriod());
                currencies.add(product.getCurrency());
            });

        return Dictionaries.builder()
            .capitalizationPeriods(capitalizationPeriods.stream().toList())
            .currencies(currencies.stream().toList())
            .periods(periods.stream().toList())
            .build();
    }

}
