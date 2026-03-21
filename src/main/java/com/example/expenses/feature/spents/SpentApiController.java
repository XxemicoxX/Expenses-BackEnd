package com.example.expenses.feature.spents;

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
@RequestMapping("spent")
@RequiredArgsConstructor
public class SpentApiController {
    private final SpentService spentService;

    @GetMapping()
    public ResponseEntity<List<SpentReaderDTO>> getAll() {
        List<SpentReaderDTO> spents = spentService.gtAllSpents();
        if (spents.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay spents registrados");
        }
        return ResponseEntity.ok(spents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpentReaderDTO> getSpent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(spentService.gtSpentById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpentReaderDTO> insertSpent(@Valid @RequestBody SpentWriterDTO spent) {
        try {
            return ResponseEntity.ok(spentService.addSpent(spent));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpentReaderDTO> updateSpent(@Valid @RequestBody SpentWriterDTO spent) {
        try {
            return ResponseEntity.ok(spentService.updSpent(spent));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpent(@PathVariable Long id) {
        try {
            spentService.dltSpent(id);
            return ResponseEntity.ok("spent eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}