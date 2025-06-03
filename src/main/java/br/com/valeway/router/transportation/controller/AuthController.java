package br.com.valeway.router.transportation.controller;

import br.com.valeway.router.transportation.config.TokenService;
import br.com.valeway.router.transportation.domain.AppUser;
import br.com.valeway.router.transportation.domain.dto.LoginRequestDTO;
import br.com.valeway.router.transportation.domain.dto.LoginResponseDTO;
import br.com.valeway.router.transportation.domain.dto.RegisterDTO;
import br.com.valeway.router.transportation.exception.UserNotFoundException;
import br.com.valeway.router.transportation.repository.UserRepository;
import br.com.valeway.router.transportation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequestDTO requestBody) {
            return authService.login(requestBody);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterDTO requestBody) {
        authService.register(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}