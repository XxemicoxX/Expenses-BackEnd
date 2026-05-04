package com.example.expenses.feature.users;

import com.example.expenses.util.RoleSystem;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record UserWriterDTO(
    Long idUser,
    @NotBlank(message = "Debes ingresar el nombre")
    String name,
    @NotBlank(message = "Debes ingresar el correo")
    @Email(message = "Correo invalido")
    String email,
    @NotBlank(message = "Debes ingresar una contraseña")
    String password,
    @NotBlank(message = "Debes ingresar un rol")
    RoleSystem role
) {
    
}
