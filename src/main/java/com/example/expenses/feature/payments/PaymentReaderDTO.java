package com.example.expenses.feature.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentReaderDTO(
    Long id,
    String name,
    @JsonProperty("id_type")
    Long type
) {

}
