package com.example.absservice.model;

import java.util.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dictionaries {

    private List<CapitalizationPeriodEntity> capitalizationPeriods;

    private List<PeriodEntity> periods;

    private List<CurrencyEntity> currencies;

}
