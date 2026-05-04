package com.example.expenses.feature.types;

import org.springframework.stereotype.Component;

import com.example.expenses.util.Mapper;

@Component
public class TypeMapper implements Mapper<Type, TypeWriterDTO, TypeReaderDTO>{
    @Override
    public Type toEntity(TypeWriterDTO dto) {
        return Type.builder()
        .idType(dto.idType())
        .name(dto.name())
        .description(dto.description())
        .build();
    }

    @Override
    public TypeReaderDTO toDto(Type entity) {
        return new TypeReaderDTO(
            entity.getIdType(),
            entity.getName(),
            entity.getDescription()
        );
    }
}
