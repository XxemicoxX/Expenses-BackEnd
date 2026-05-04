package com.example.expenses.feature.users;

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
@RequestMapping("users")
@RequiredArgsConstructor
public class UserApiController {
     private final UserService userService;

     @GetMapping()
     public ResponseEntity<List<UserReaderDTO>> getAll() {
          List<UserReaderDTO> users = userService.gtAllUsers();
          if (users.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No hay users registrados");
          }
          return ResponseEntity.ok(users);
     }

     @GetMapping("/{id}")
     public ResponseEntity<UserReaderDTO> getUser(@PathVariable Long id) {
          try {
               return ResponseEntity.ok(userService.gtUserById(id));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
          }
     }

     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<UserReaderDTO> insertUser(@Valid @RequestBody UserWriterDTO user) {
          try {
               return ResponseEntity.ok(userService.addUser(user));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<UserReaderDTO> updateUser(@Valid @RequestBody UserWriterDTO user) {
          try {
               return ResponseEntity.ok(userService.updUser(user));
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteUser(@PathVariable Long id) {
          try {
               userService.dltUser(id);
               return ResponseEntity.ok("user eliminado");
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
          }
     }
}
