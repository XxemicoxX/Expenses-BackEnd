package com.example.expenses.feature.payments;
import org.springframework.stereotype.Component;

import com.example.expenses.feature.types.Type;
import com.example.expenses.util.Mapper;

@Component
public class PaymentMapper implements Mapper<Payment, PaymentWriterDTO, PaymentReaderDTO> {

    @Override
    public Payment toEntity(PaymentWriterDTO dto) {
        return Payment.builder()
            .idPayment(dto.id())
            .name(dto.name())
            .type(Type.builder().idType(dto.idType()).build())
            .build();
    }

    @Override
    public PaymentReaderDTO toDto(Payment entity) {
        return new PaymentReaderDTO(
            entity.getIdPayment(),
            entity.getName(),
            entity.getType().getIdType()
        );
    }
}
