package br.com.valeway.router.transportation.service;

import br.com.valeway.router.transportation.config.TokenService;
import br.com.valeway.router.transportation.domain.AppUser;
import br.com.valeway.router.transportation.domain.UserType;
import br.com.valeway.router.transportation.domain.dto.LoginRequestDTO;
import br.com.valeway.router.transportation.domain.dto.LoginResponseDTO;
import br.com.valeway.router.transportation.domain.dto.RegisterDTO;
import br.com.valeway.router.transportation.exception.BadRequestException;
import br.com.valeway.router.transportation.exception.InvalidCredentialsException;
import br.com.valeway.router.transportation.exception.UserAlreadyExistsException;
import br.com.valeway.router.transportation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public ResponseEntity login(LoginRequestDTO requestBody) {

        AppUser user = userRepository.findByEmail(requestBody.email()).orElseThrow(() -> new InvalidCredentialsException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(requestBody.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Usuário ou senha inválidos");
        }
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
    }

    public void register(RegisterDTO requestBody) {
        Optional<AppUser> user = userRepository.findByEmail(requestBody.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Usuário já cadastrado com o email: " + requestBody.getEmail());
        }
            AppUser newUser = new AppUser();
            newUser.setPassword(passwordEncoder.encode(requestBody.getPassword()));
            newUser.setName(requestBody.getName());
            newUser.setEmail(requestBody.getEmail());
            newUser.setIdentificationNumber(requestBody.getIdentificationNumber());

        String idNumber = requestBody.getIdentificationNumber().replaceAll("[^\\d]", "");
        if (idNumber.length() == 11) {
            newUser.setUserType(UserType.EMPLOYEE.name());
        } else if (idNumber.length() == 14) {
            newUser.setUserType(UserType.COMPANY.name());
        } else {
            throw new BadRequestException("Número de identificação inválido.");
        }

        userRepository.save(newUser);
    }
}