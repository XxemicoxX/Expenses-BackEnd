package com.example.expenses.feature.categories;

import org.springframework.stereotype.Component;

import com.example.expenses.util.Mapper;

@Component
public class CategorieMapper implements Mapper<Categorie, CategorieWriterDTO, CategorieReaderDTO>{
    @Override
    public Categorie toEntity(CategorieWriterDTO dto){
        return Categorie.builder()
        .id(dto.id())
        .name(dto.name())
        .description(dto.description())
        .build();
    }

    @Override
    public CategorieReaderDTO toDto(Categorie entity) {
        return new CategorieReaderDTO(
            entity.getId(),
            entity.getName(),
            entity.getDescription()
        );
    }
}
 