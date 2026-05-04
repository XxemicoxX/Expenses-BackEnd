package com.example.expenses.feature.users;

import com.example.expenses.util.RoleSystem;

public record UserReaderDTO(
    Long idUser,
    String name,
    String email,
    String contrasena,
    RoleSystem role
) {
    
} 
