package com.example.expenses.api; 

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.expenses.auth.CustomUserDetail;
import com.example.expenses.auth.JwtService;
import com.example.expenses.feature.users.User;
import com.example.expenses.feature.users.UserRepository;
import com.example.expenses.util.AuthenticationRequest;
import com.example.expenses.util.AuthenticationResponse;
import com.example.expenses.util.RefreshTokenRequest;
import com.example.expenses.util.RegisterRequest;
import com.example.expenses.util.RoleSystem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(RoleSystem.USER)   // ← tu enum se llama RoleSystem
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(new CustomUserDetail(user), user.getIdUser());
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetail(user), user.getIdUser());
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.contrasena()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        var jwtToken = jwtService.generateToken(new CustomUserDetail(user), user.getIdUser());
        var refreshToken = jwtService.generateRefreshToken(new CustomUserDetail(user), user.getIdUser());
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        String userEmail = jwtService.extractUsername(request.refreshToken());
        if (userEmail != null) {
            var user = userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(request.refreshToken(), new CustomUserDetail(user))) {
                var accessToken = jwtService.generateToken(new CustomUserDetail(user), user.getIdUser());
                return new AuthenticationResponse(accessToken, request.refreshToken());
            }
        }
        throw new RuntimeException("Token de refresco INVALIDO");
    }
}