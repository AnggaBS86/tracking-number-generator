package com.angga.numgen.controller;


import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
public class TrackingRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Must be a valid ISO 3166-1 alpha-2 country code")
    private String origin_country_id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Must be a valid ISO 3166-1 alpha-2 country code")
    private String destination_country_id;

    @NotNull
    @Digits(integer = 5, fraction = 3, message = "Weight can have up to 3 decimal places")
    @DecimalMin(value = "0.001", message = "Weight must be at least 0.001")
    private BigDecimal weight;

    @NotBlank
    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?([+-]\\d{2}:\\d{2}|Z)$",
        message = "Must be a valid RFC 3339 timestamp"
    )
    private String created_at;

    @NotNull
    private UUID customer_id;

    @NotBlank
    private String customer_name;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Must be in slug-case/kebab-case")
    private String customer_slug;
}
