package com.example.absservice.service;

import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.absservice.model.*;
import com.example.absservice.repository.*;

@Service
@RequiredArgsConstructor
public class FinancialProductsService {

    private final FinancialProductsRepository financialProductsRepository;

    public List<FinancialProductEntity> getFinancialProducts() {
        return financialProductsRepository.findAll();
    }

}
