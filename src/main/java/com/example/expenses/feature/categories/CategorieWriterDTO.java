package com.example.expenses.feature.categories;

import jakarta.validation.constraints.NotBlank;

public record CategorieWriterDTO(
    Long id,
    @NotBlank(message = "Debe ingresar el nombre")
    String name,
    @NotBlank(message = "Ingrese una descripcion breve")
    String description
) {
     
}
