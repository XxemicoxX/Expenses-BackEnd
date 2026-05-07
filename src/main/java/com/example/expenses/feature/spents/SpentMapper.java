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
            .idSpent(dto.idSpent())
            .name(dto.name())
            .amount(dto.amount())
            .description(dto.description())
            .date(dto.date())
            .hour(dto.hour())
            .payment(Payment.builder().idPayment(dto.idPayment()).build())
            .categorie(Categorie.builder().idCategorie(dto.idCategorie()).build())
            .user(User.builder().idUser(dto.idUser()).build())
            .build();
    }

    @Override
    public SpentReaderDTO toDto(Spent entity) {
        return new SpentReaderDTO(
            entity.getIdSpent(),
            entity.getName(),
            entity.getAmount(),
            entity.getDescription(),
            entity.getDate(),
            entity.getHour(),
            entity.getPayment().getIdPayment(),
            entity.getCategorie().getIdCategorie(),
            entity.getUser().getIdUser()
        );
    }
}