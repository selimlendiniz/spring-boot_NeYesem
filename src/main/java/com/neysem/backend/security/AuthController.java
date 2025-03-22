package com.neysem.backend.security;

import com.neysem.backend.model.Customer;
import com.neysem.backend.model.Manager;
import com.neysem.backend.model.Role;
import com.neysem.backend.model.User;
import com.neysem.backend.repo.CustomerRepository;
import com.neysem.backend.repo.ManagerRepository;
import com.neysem.backend.security.dto.AuthRequest;
import com.neysem.backend.security.dto.AuthResponse;
import com.neysem.backend.security.dto.RefreshTokenRequest;
import com.neysem.backend.security.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = findUserByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        String username = tokenService.extractUsername(request.getRefreshToken());

        User user = findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String newAccessToken = tokenService.generateAccessToken(user);
        return ResponseEntity.ok(new AuthResponse(newAccessToken, request.getRefreshToken()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (findUserByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }

        if (request.getRole() == Role.MANAGER) {
            Manager newUser = Manager.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.MANAGER)
                    .email(request.getEmail())
                    .name(request.getUsername())
                    .surname(request.getSurname())
                    .build();
            managerRepository.save(newUser);
        } else {
            Customer newUser = Customer.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.CUSTOMER)
                    .email(request.getEmail())
                    .name(request.getUsername())
                    .surname(request.getSurname())
                    .build();
            customerRepository.save(newUser);
        }

        return ResponseEntity.ok("User registered successfully.");
    }

    private Optional<User> findUserByUsername(String username) {
        return customerRepository.findByUsername(username)
                .map(c -> (User) c)
                .or(() -> managerRepository.findByUsername(username).map(m -> (User) m));
    }
}
