package com.example.expenses.feature.incomes;

import java.time.LocalDate;

import com.example.expenses.feature.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncome;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false, length = 50, unique = true)
    private String source;
    private LocalDate date;
    @Column(nullable = false, length = 50)
    private String description;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
