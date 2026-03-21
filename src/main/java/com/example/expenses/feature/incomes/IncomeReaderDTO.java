package com.example.expenses.feature.incomes;

import java.time.LocalDate;

import com.example.expenses.feature.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public record IncomeReaderDTO(
    Long id,
    Double amount,
    String source,
    LocalDate date,
    String description,
    @JsonProperty("id_user")
    Long user
) {

}