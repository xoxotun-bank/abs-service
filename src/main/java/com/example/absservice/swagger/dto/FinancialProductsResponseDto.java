package com.example.absservice.swagger.dto;

import java.util.*;

import javax.validation.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * FinancialProductsResponseDto
 */
@Validated

public class FinancialProductsResponseDto {

    @JsonProperty("products")
    @Valid
    private List<AbsFinancialProductDto> products = null;

    public FinancialProductsResponseDto products(List<AbsFinancialProductDto> products) {
        this.products = products;
        return this;
    }

    public FinancialProductsResponseDto addProductsItem(AbsFinancialProductDto productsItem) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(productsItem);
        return this;
    }

    /**
     * Список вкладов
     *
     * @return products
     **/
    @Schema(description = "Список вкладов")
    @Valid
    public List<AbsFinancialProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<AbsFinancialProductDto> products) {
        this.products = products;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinancialProductsResponseDto financialProductsResponseDto = (FinancialProductsResponseDto) o;
        return Objects.equals(this.products, financialProductsResponseDto.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {

        String sb = "class FinancialProductsResponseDto {\n" +
                    "    products: " + toIndentedString(products) + "\n" +
                    "}";
        return sb;
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
