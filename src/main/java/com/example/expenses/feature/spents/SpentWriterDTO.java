package com.example.expenses.feature.spents;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.expenses.feature.categories.Categorie;
import com.example.expenses.feature.payments.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpentWriterDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotNull(message = "Debe ingresar el monto")
    Double amount,
    @NotBlank(message = "Ingrese una descripcion breve")
    String description,
    LocalDate date,
    LocalTime hour,
    @NotNull(message = "Debe seleccionar un pago")
    @JsonProperty("id_payment_method")
    Payment payment,
    @NotNull(message = "Debe seleccionar una categoria")
    @JsonProperty("id_categorie")
    Categorie categorie
) {

}