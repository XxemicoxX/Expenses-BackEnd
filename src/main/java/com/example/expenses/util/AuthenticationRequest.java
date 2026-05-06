package com.example.expenses.util;

public record AuthenticationRequest(
    String email,
    String contrasena
) {
}
