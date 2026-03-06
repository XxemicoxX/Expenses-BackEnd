package com.example.expenses.feature.types;

import jakarta.validation.constraints.NotBlank;

public record TypeWriterDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotBlank(message = "Ingrese una descripcion breve")
    String description
) {
    
} 
