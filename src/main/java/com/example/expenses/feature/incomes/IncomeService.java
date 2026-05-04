package com.example.expenses.feature.incomes;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

    public List<IncomeReaderDTO> gtAllIncomes() {
        return incomeRepository.findAll().stream().map(incomeMapper::toDto).toList();
    }

    public IncomeReaderDTO gtIncomeById(Long id) {
        return incomeMapper.toDto(incomeRepository.findById(id).orElseThrow());
    }

    public IncomeReaderDTO addIncome(IncomeWriterDTO income) {
        return save(income);
    }

    public IncomeReaderDTO updIncome(IncomeWriterDTO income) throws Exception {
        if (!incomeRepository.existsById(income.idIncome())) {
            throw new Exception("ID no encontrado");
        }
        return save(income);
    }

    public String dltIncome(Long id) throws Exception {
        if (!incomeRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        incomeRepository.deleteById(id);
        return String.format("Income eliminado con el ID: %d", id);
    }

    private IncomeReaderDTO save(IncomeWriterDTO income) {
        return incomeMapper.toDto(incomeRepository.save(incomeMapper.toEntity(income)));
    }
}