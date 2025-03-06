package com.example.absservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.example.absservice.model.*;

public interface FinancialProductsRepository extends JpaRepository<FinancialProductEntity, Long>,
    JpaSpecificationExecutor<FinancialProductEntity> {

    @EntityGraph(value = "financial-products_entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<FinancialProductEntity> findAll();

}
