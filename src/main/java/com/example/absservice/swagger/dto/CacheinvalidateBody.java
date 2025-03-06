package com.example.absservice.swagger.dto;

import java.time.*;
import java.util.*;

import javax.validation.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.validation.annotation.*;

/**
 * CacheinvalidateBody
 */
@Validated

public class CacheinvalidateBody {

    @JsonProperty("date")
    private OffsetDateTime date = null;

    public CacheinvalidateBody date(OffsetDateTime date) {
        this.date = date;
        return this;
    }

    /**
     * Дата сброса кэша
     *
     * @return date
     **/
    @Schema(example = "2022-10-12T15:30Z", required = true, description = "Дата сброса кэша")
    @NotNull

    @Valid
    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CacheinvalidateBody cacheinvalidateBody = (CacheinvalidateBody) o;
        return Objects.equals(this.date, cacheinvalidateBody.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {

        String sb = "class CacheinvalidateBody {\n" +
                    "    date: " + toIndentedString(date) + "\n" +
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
