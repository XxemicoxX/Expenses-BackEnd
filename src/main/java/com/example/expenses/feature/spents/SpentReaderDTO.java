package com.example.expenses.feature.spents;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpentReaderDTO(
    Long idSpent,
    String name,
    Double amount,
    String description,
    LocalDate date,
    LocalTime hour,
    @JsonProperty("idPayment")
    Long idPayment,
    @JsonProperty("idCategorie")
    Long idCategorie,
    @JsonProperty("idUser")
    Long idUser
) {

}