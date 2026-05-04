package com.example.expenses.feature.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentWriterDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotNull(message = "Debe seleccionar un tipo")
    @JsonProperty("idType")
    Long idType
) {

}
