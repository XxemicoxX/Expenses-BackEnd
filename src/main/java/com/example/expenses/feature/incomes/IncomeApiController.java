package com.example.expenses.feature.incomes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("income")
@RequiredArgsConstructor
public class IncomeApiController {
    private final IncomeService incomeService;

    @GetMapping()
    public ResponseEntity<List<IncomeReaderDTO>> getAll() {
        List<IncomeReaderDTO> incomes = incomeService.gtAllIncomes();
        if (incomes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay incomes registrados");
        }
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeReaderDTO> getIncome(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incomeService.gtIncomeById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomeReaderDTO> insertIncome(@Valid @RequestBody IncomeWriterDTO income) {
        try {
            System.out.println("DTO recibido: " + income);
            System.out.println("idUser: " + income.idUser());
            return ResponseEntity.ok(incomeService.addIncome(income));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomeReaderDTO> updateIncome(@Valid @RequestBody IncomeWriterDTO income) {
        try {
            return ResponseEntity.ok(incomeService.updIncome(income));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.dltIncome(id);
            return ResponseEntity.ok("income eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
