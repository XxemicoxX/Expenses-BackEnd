package com.example.expenses.feature.incomes;

import org.springframework.stereotype.Component;

import com.example.expenses.feature.users.User;
import com.example.expenses.util.Mapper;

@Component
public class IncomeMapper implements Mapper<Income, IncomeWriterDTO, IncomeReaderDTO> {

    @Override
    public Income toEntity(IncomeWriterDTO dto) {
        return Income.builder()
            .idIncome(dto.idIncome())
            .amount(dto.amount())
            .source(dto.source())
            .date(dto.date())
            .description(dto.description())
            .user(User.builder().idUser(dto.idUser()).build())
            .build();
    }

    @Override
    public IncomeReaderDTO toDto(Income entity) {
        return new IncomeReaderDTO(
            entity.getIdIncome(),
            entity.getAmount(),
            entity.getSource(),
            entity.getDate(),
            entity.getDescription(),
            entity.getUser().getIdUser()
        );
    }
}