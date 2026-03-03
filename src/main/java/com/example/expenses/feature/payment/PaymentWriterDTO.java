package com.example.expenses.feature.payment;

import com.example.expenses.feature.type.Type;

public record PaymentWriterDTO(
    Long id,
    String name,
    Type type
) {
    
}
