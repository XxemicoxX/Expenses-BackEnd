package com.example.expenses.feature.users;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserReaderDTO> gtAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserReaderDTO gtUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public UserReaderDTO addUser(UserWriterDTO user) {
        return save(user);
    }

    public UserReaderDTO updUser(UserWriterDTO user) throws Exception {
        if (!userRepository.existsById(user.idUser())) {
            throw new Exception("ID no encontrado");
        }
        return save(user);
    }

    public String dltUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        userRepository.deleteById(id);
        return String.format("User eliminado con el ID: %d", id);
    }

    // Método guardar
    private UserReaderDTO save(UserWriterDTO user) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
