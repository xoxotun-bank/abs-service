package com.example.absservice.swagger.dto;

import java.time.*;
import java.util.*;

import javax.validation.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * ClientDto
 */
@Validated

public class ClientDto {

    @JsonProperty("categories")
    @Valid
    private List<String> categories = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("birthDate")
    private LocalDate birthDate = null;

    @JsonProperty("passport")
    private String passport = null;

    public ClientDto categories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public ClientDto addCategoriesItem(String categoriesItem) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }
        this.categories.add(categoriesItem);
        return this;
    }

    /**
     * Get categories
     *
     * @return categories
     **/
    @Schema(example = "[\"Сотрудник\",\"Клиент\"]", description = "")

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public ClientDto name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Фамилия Имя Отчество
     *
     * @return name
     **/
    @Schema(example = "Иванов Иван Иванович", description = "Фамилия Имя Отчество")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientDto birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Дата рождения клиента, формат ГГГГ-ММ-ДД
     *
     * @return birthDate
     **/
    @Schema(example = "Wed May 15 11:00:00 VLAST 1985", description = "Дата рождения клиента, формат ГГГГ-ММ-ДД")

    @Valid
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ClientDto passport(String passport) {
        this.passport = passport;
        return this;
    }

    /**
     * Серия и номер паспорта клиента, 10 цифр
     *
     * @return passport
     **/
    @Schema(example = "0000000000", description = "Серия и номер паспорта клиента, 10 цифр")

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(this.categories, clientDto.categories) &&
               Objects.equals(this.name, clientDto.name) &&
               Objects.equals(this.birthDate, clientDto.birthDate) &&
               Objects.equals(this.passport, clientDto.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories, name, birthDate, passport);
    }

    @Override
    public String toString() {

        String sb = "class ClientDto {\n" +
                    "    categories: " + toIndentedString(categories) + "\n" +
                    "    name: " + toIndentedString(name) + "\n" +
                    "    birthDate: " + toIndentedString(birthDate) + "\n" +
                    "    passport: " + toIndentedString(passport) + "\n" +
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
