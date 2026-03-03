package com.example.expenses.feature.payment;


import com.example.expenses.feature.type.Type;

import jakarta.validation.constraints.NotBlank;

public record PaymentReaderDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotBlank(message = "Ingrese un tipo de pago")
    Type type
) {
    
}
