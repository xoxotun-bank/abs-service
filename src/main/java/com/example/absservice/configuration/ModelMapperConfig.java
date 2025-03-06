package com.example.absservice.configuration;

import java.util.*;

import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.context.annotation.*;

import static org.modelmapper.config.Configuration.AccessLevel.*;

import com.example.absservice.model.*;
import com.example.absservice.swagger.dto.*;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        financialProductMapperConfig(mapper);
        clientProductMapperConfig(mapper);
        dictionariesMapperConfig(mapper);
        absFinancialProductMapperConfig(mapper);
        mapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(PRIVATE)
            .setSkipNullEnabled(true);
        return mapper;
    }

    private void financialProductMapperConfig(ModelMapper modelMapper) {
        modelMapper
            .createTypeMap(FinancialProductEntity.class, FinancialProductDto.class)
            .addMappings(mapper -> {
                    mapper.using(
                            (Converter<PeriodEntity, Integer>) src ->
                                src.getSource().getValue()
                        )
                        .map(FinancialProductEntity::getPeriod, FinancialProductDto::setPeriod);
                    mapper.using(
                            (Converter<CategoryEntity, String>) src ->
                                src.getSource().getName()
                        )
                        .map(FinancialProductEntity::getCategory, FinancialProductDto::setCategory);
                    mapper.using(
                            (Converter<CurrencyEntity, String>) src -> src.getSource().getCurrency()
                        )
                        .map(FinancialProductEntity::getCurrency, FinancialProductDto::setCurrency);
                    mapper.using(
                            (Converter<CapitalizationPeriodEntity, String>) src -> src.getSource()
                                .getValue()
                        )
                        .map(
                            FinancialProductEntity::getCapitalizationPeriod,
                            FinancialProductDto::capitalizationPeriod);
                }
            );
    }

    private void absFinancialProductMapperConfig(ModelMapper modelMapper) {
        modelMapper
            .createTypeMap(FinancialProductEntity.class, AbsFinancialProductDto.class)
            .addMappings(mapper -> {
                    mapper
                        .using((Converter<FinancialProductEntity, FinancialProductDto>)
                            src -> modelMapper.map(src.getSource(), FinancialProductDto.class))
                        .map(src -> src, AbsFinancialProductDto::setFinancialProduct);
                }
            );
    }

    private void clientProductMapperConfig(ModelMapper modelMapper) {
        modelMapper.createTypeMap(ClientEntity.class, ClientDto.class)
            .addMappings(mapper ->
                mapper.using(
                    (Converter<List<CategoryEntity>, List<String>>) src ->
                        src.getSource().stream()
                            .map(CategoryEntity::getName)
                            .toList()
                ).map(ClientEntity::getCategories, ClientDto::setCategories)
            );
    }

    private void dictionariesMapperConfig(ModelMapper modelMapper) {
        modelMapper.createTypeMap(Dictionaries.class, ParametersDto.class)
            .addMappings(mapper -> {
                mapper.using(
                    (Converter<List<PeriodEntity>, List<Integer>>) src -> src.getSource().stream()
                        .map(PeriodEntity::getValue)
                        .toList()
                ).map(Dictionaries::getPeriods, ParametersDto::setPeriods);
                mapper.using(
                    (Converter<List<CapitalizationPeriodEntity>, List<String>>) src ->
                        src.getSource().stream()
                            .map(CapitalizationPeriodEntity::getValue)
                            .toList()
                ).map(
                    Dictionaries::getCapitalizationPeriods,
                    ParametersDto::setCapitalizationPeriods);
                mapper.using(
                    (Converter<List<CurrencyEntity>, List<String>>) src ->
                        src.getSource().stream()
                            .map(CurrencyEntity::getCurrency)
                            .toList()
                ).map(Dictionaries::getCurrencies, ParametersDto::setCurrencies);
            });
    }

}
