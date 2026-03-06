package com.example.expenses.feature.payments;


import com.example.expenses.feature.types.Type;

import jakarta.validation.constraints.NotBlank;

public record PaymentReaderDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotBlank(message = "Ingrese un tipo de pago")
    Type type
) {
    
}
