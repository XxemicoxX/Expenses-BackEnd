package com.example.expenses.feature.users;

import org.springframework.stereotype.Component;

import com.example.expenses.util.Mapper;

@Component
public class UserMapper implements Mapper<User, UserWriterDTO, UserReaderDTO>{
    @Override
    public User toEntity(UserWriterDTO dto) {
        return User.builder()
        .idUser(dto.idUser())
        .name(dto.name())
        .email(dto.email())
        .password(dto.password())
        .role(dto.role())
        .build();
    }

    @Override
    public UserReaderDTO toDto(User entity) {
       return new UserReaderDTO(
        entity.getIdUser(),
        entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        entity.getRole()
       );
    }
}
