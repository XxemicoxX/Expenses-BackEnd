package com.example.expenses.feature.users;

import org.springframework.stereotype.Component;

import com.example.expenses.util.Mapper;

@Component
public class UserMapper implements Mapper<User, UserWriterDTO, UserReaderDTO>{
    @Override
    public User toEntity(UserWriterDTO dto) {
        return User.builder()
        .id(dto.id())
        .name(dto.name())
        .email(dto.email())
        .password(dto.password())
        .role(dto.role())
        .build();
    }

    @Override
    public UserReaderDTO toDto(User entity) {
       return new UserReaderDTO(
        entity.getId(),
        entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        entity.getRole()
       );
    }
}
