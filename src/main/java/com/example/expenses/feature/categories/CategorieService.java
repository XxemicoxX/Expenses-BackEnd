package com.example.expenses.feature.categories;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategorieService {
    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    public List<CategorieReaderDTO> gtAllCategories() {
        return categorieRepository.findAll().stream().map(categorieMapper::toDto).toList();
    }

    public CategorieReaderDTO gtCategorieById(Long id) {
        return categorieMapper.toDto(categorieRepository.findById(id).orElseThrow());
    }

    public CategorieReaderDTO addCategorie(CategorieWriterDTO categorie) {
        return save(categorie);
    }

    public CategorieReaderDTO updCategorie(CategorieWriterDTO categorie) throws Exception {
        if (!categorieRepository.existsById(categorie.idCategorie())) {
            throw new Exception("ID no encontrado");
        }
        return save(categorie);
    }

    public String dltCategorie(Long id) throws Exception {
        System.out.println("=== INTENTANDO ELIMINAR ID: " + id);
        System.out.println("=== EXISTE: " + categorieRepository.existsById(id));
        System.out.println(
                "=== TODOS LOS IDS: " + categorieRepository.findAll().stream().map(c -> c.getIdCategorie()).toList());

        if (!categorieRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        categorieRepository.deleteById(id);
        return String.format("Categorie eliminado con el ID: %d", id);
    }

    // Método guardar
    private CategorieReaderDTO save(CategorieWriterDTO categorie) {
        return categorieMapper.toDto(categorieRepository.save(categorieMapper.toEntity(categorie)));
    }
}
