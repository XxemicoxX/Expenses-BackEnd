package com.example.expenses.util;

public record RegisterRequest(
    String name,
    String email,
    String password
) {}
