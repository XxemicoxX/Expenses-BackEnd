package com.example.expenses.feature.types;

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
@RequestMapping("type")
@RequiredArgsConstructor
public class TypeApiController {
    private final TypeService typeService;

    @GetMapping()
    public ResponseEntity<List<TypeReaderDTO>> getAll() {
        List<TypeReaderDTO> types = typeService.gtAllTypes();
        if (types.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay types registrados");
        }
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReaderDTO> getType(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(typeService.gtTypeById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeReaderDTO> insertType(@Valid @RequestBody TypeWriterDTO type) {
        try {
            return ResponseEntity.ok(typeService.addType(type));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeReaderDTO> updateType(@Valid @RequestBody TypeWriterDTO type) {
        try {
            return ResponseEntity.ok(typeService.updType(type));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteType(@PathVariable Long id) {
        try {
            typeService.dltType(id);
            return ResponseEntity.ok("type eliminado");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
