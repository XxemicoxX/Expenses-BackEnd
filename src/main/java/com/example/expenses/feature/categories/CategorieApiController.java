package com.example.expenses.feature.categories;

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
@RequestMapping("categorie")
@RequiredArgsConstructor
public class CategorieApiController {
    private final CategorieService categorieService;

    @GetMapping()
    public ResponseEntity<List<CategorieReaderDTO>> getAll() {
        List<CategorieReaderDTO> categories = categorieService.gtAllCategories();
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay categories registrados");
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieReaderDTO> getCategorie(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categorieService.gtCategorieById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorieReaderDTO> insertCategorie(@Valid @RequestBody CategorieWriterDTO categorie) {
        try {
            return ResponseEntity.ok(categorieService.addCategorie(categorie));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorieReaderDTO> updateCategorie(@Valid @RequestBody CategorieWriterDTO categorie) {
        try {
            return ResponseEntity.ok(categorieService.updCategorie(categorie));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable Long id) {
        try {
            categorieService.dltCategorie(id);
            return ResponseEntity.ok("categorie eliminado");
        } catch (Exception e) {
            e.printStackTrace(); // ← agrega esto para ver el stack trace completo
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
