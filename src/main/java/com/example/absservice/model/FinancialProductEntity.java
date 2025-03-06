package com.example.absservice.model;

import java.math.*;

import jakarta.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "financial_products")
@NamedEntityGraph(
    name = "financial-products_entity-graph",
    attributeNodes = {
        @NamedAttributeNode("currency"),
        @NamedAttributeNode("category"),
        @NamedAttributeNode("period"),
        @NamedAttributeNode("capitalizationPeriod")
    }
)
public class FinancialProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "min_sum", nullable = false)
    private BigDecimal minSum;

    @NotNull
    @Column(name = "max_sum", nullable = false)
    private BigDecimal maxSum;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "period_id", nullable = false)
    private PeriodEntity period;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @NotNull
    @Column(name = "can_deposit", nullable = false)
    private Boolean canDeposit = false;

    @NotNull
    @Column(name = "can_withdrawal", nullable = false)
    private Boolean canWithdrawal = false;

    @NotNull
    @Column(name = "percent", nullable = false)
    private BigDecimal percent;

    @NotNull
    @Column(name = "capitalization_to_same_account", nullable = false)
    private Boolean capitalizationToSameAccount = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capitalization_period_id")
    private CapitalizationPeriodEntity capitalizationPeriod;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false)
    private CurrencyEntity currency;

}
