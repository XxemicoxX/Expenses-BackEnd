package com.example.expenses.feature.spents;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpentService {
    private final SpentRepository spentRepository;
    private final SpentMapper spentMapper;

    public List<SpentReaderDTO> gtAllSpents() {
        return spentRepository.findAll().stream().map(spentMapper::toDto).toList();
    }

    public SpentReaderDTO gtSpentById(Long id) {
        return spentMapper.toDto(spentRepository.findById(id).orElseThrow());
    }

    public SpentReaderDTO addSpent(SpentWriterDTO spent) {
        return save(spent);
    }

    public SpentReaderDTO updSpent(SpentWriterDTO spent) throws Exception {
        if (!spentRepository.existsById(spent.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(spent);
    }

    public String dltSpent(Long id) throws Exception {
        if (!spentRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        spentRepository.deleteById(id);
        return String.format("Spent eliminado con el ID: %d", id);
    }

    private SpentReaderDTO save(SpentWriterDTO spent) {
        return spentMapper.toDto(spentRepository.save(spentMapper.toEntity(spent)));
    }
}