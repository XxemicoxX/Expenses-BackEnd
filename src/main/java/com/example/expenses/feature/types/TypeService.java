package com.example.expenses.feature.types;

import java.util.List;

import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
  
    public List<TypeReaderDTO> gtAllTypes(){
        return typeRepository.findAll().stream().map(typeMapper::toDto).toList();
    }

    public TypeReaderDTO gtTypeById(Long id){
        return typeMapper.toDto(typeRepository.findById(id).orElseThrow());
    }

    public TypeReaderDTO addType(TypeWriterDTO type){
       return save(type);
    }

    public TypeReaderDTO updType(TypeWriterDTO type) throws Exception{
        if (!typeRepository.existsById(type.idType())) {
            throw new Exception("ID no encontrado");
        }
        return save(type);
    }

    public String dltType(Long id) throws Exception{
        if (!typeRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        typeRepository.deleteById(id);
        return String.format("Type eliminado con el ID: %d", id);
    }

    //Método guardar
    private TypeReaderDTO save(TypeWriterDTO type){
        return typeMapper.toDto(typeRepository.save(typeMapper.toEntity(type)));
    }
}
