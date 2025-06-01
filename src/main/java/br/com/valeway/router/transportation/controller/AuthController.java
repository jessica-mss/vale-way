package br.com.valeway.router.transportation.controller;

import br.com.valeway.router.transportation.config.TokenService;
import br.com.valeway.router.transportation.domain.AppUser;
import br.com.valeway.router.transportation.domain.dto.LoginRequestDTO;
import br.com.valeway.router.transportation.domain.dto.LoginResponseDTO;
import br.com.valeway.router.transportation.domain.dto.RegisterDTO;
import br.com.valeway.router.transportation.exception.UserNotFoundException;
import br.com.valeway.router.transportation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequestDTO requestBody) {
//        User user = userRepository.findByEmail(requestBody.email()).orElseThrow(() -> new RuntimeException("User not found"));
        AppUser user = userRepository.findByEmail(requestBody.email()).orElseThrow(() -> new UserNotFoundException("Nenhumm usuário encontrado para o email: " + requestBody.email()));

        if (passwordEncoder.matches(requestBody.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterDTO requestBody) {

        Optional<AppUser> user = userRepository.findByEmail(requestBody.getEmail());

        if (user.isEmpty()) {
            AppUser newUser = new AppUser();
            newUser.setPassword(passwordEncoder.encode(requestBody.getPassword()));
            newUser.setName(requestBody.getName());
            newUser.setUserType(requestBody.getUserType());
            newUser.setEmail(requestBody.getEmail());
            newUser.setIdentificationNumber(requestBody.getIdentificationNumber());

            try {
                userRepository.save(newUser);
                return new ResponseEntity(HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace(); // Veja o erro real no console
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar usuário");
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
