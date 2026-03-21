package com.example.expenses.feature.spents;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.expenses.feature.categories.Categorie;
import com.example.expenses.feature.payments.Payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Spent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spent")
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false, length = 100)
    private String description;
    private LocalDate date;
    private LocalTime hour;
    @ManyToOne
    @JoinColumn(name = "id_payment_method", nullable = false)
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;
}
