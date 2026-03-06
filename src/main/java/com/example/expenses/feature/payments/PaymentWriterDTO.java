package com.example.expenses.feature.payments;

import com.example.expenses.feature.types.Type;

public record PaymentWriterDTO(
    Long id,
    String name,
    Type type
) {
    
}
