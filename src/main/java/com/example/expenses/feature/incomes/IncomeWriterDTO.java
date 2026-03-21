package com.example.expenses.feature.incomes;

import java.time.LocalDate;

import com.example.expenses.feature.users.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IncomeWriterDTO(
    Long id,
    @NotNull(message = "Debe ingresar el monto")
    Double amount,
    @NotBlank(message = "Debe ingresar la fuente")
    String source,
    LocalDate date,
    @NotBlank(message = "Ingrese una descripcion breve")
    String description,
    @NotNull(message = "Debe asignar un usuario")
    @JsonProperty("id_user")
    User user
) {

}