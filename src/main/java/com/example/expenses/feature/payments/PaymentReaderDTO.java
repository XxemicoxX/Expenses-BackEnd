package com.example.expenses.feature.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentReaderDTO(
    Long idPayment,
    String name,
    @JsonProperty("idType")
    Long idType
) {

}
