package com.example.expenses.feature.incomes;

import java.time.LocalDate;


public record IncomeReaderDTO(
    Long idIncome,
    Double amount,
    String source,
    LocalDate date,
    String description,
    Long idUser
) {

}