package com.example.expenses.feature.spents;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpentReaderDTO(
    Long id,
    String name,
    Double amount,
    String description,
    LocalDate date,
    LocalTime hour,
    @JsonProperty("id_payment_method")
    Long payment,
    @JsonProperty("id_categorie")
    Long categorie
) {

}