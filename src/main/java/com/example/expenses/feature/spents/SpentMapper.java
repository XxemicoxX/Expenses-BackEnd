package com.example.expenses.feature.spents;
import org.springframework.stereotype.Component;

import com.example.expenses.feature.categories.Categorie;
import com.example.expenses.feature.payments.Payment;
import com.example.expenses.feature.users.User;
import com.example.expenses.util.Mapper;

@Component
public class SpentMapper implements Mapper<Spent, SpentWriterDTO, SpentReaderDTO> {

    @Override
    public Spent toEntity(SpentWriterDTO dto) {
        return Spent.builder()
            .id(dto.id())
            .name(dto.name())
            .amount(dto.amount())
            .description(dto.description())
            .date(dto.date())
            .hour(dto.hour())
            .payment(Payment.builder().id(dto.payment().getId()).build())
            .categorie(Categorie.builder().id(dto.categorie().getId()).build())
            .user(User.builder().id(dto.user().getId()).build())
            .build();
    }

    @Override
    public SpentReaderDTO toDto(Spent entity) {
        return new SpentReaderDTO(
            entity.getId(),
            entity.getName(),
            entity.getAmount(),
            entity.getDescription(),
            entity.getDate(),
            entity.getHour(),
            entity.getPayment().getId(),
            entity.getCategorie().getId(),
            entity.getUser().getId()
        );
    }
}