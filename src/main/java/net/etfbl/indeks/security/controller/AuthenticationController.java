package net.etfbl.indeks.security.controller;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.security.dto.LoginAccountDTO;
import net.etfbl.indeks.security.dto.RegisterAccountDTO;
import net.etfbl.indeks.security.enumeration.RegistrationStatus;
import net.etfbl.indeks.security.service.AuthenticationService;
import net.etfbl.indeks.security.service.JwtService;
import net.etfbl.indeks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAccountDTO registerUserDto) {
        RegistrationStatus registrationStatus = authenticationService.signup(registerUserDto);

        switch (registrationStatus) {
            case SUCCESS -> {
                return ResponseEntity.ok(Map.of("message", "Registration successful!"));
            }
            case INVALID_FLAG -> {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid account type flag provided in the request."));
            }
            case ACCOUNT_ALREADY_EXISTS -> {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Account already exists. Please try logging in."));
            }
            default -> {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Unexpected error occurred."));
            }
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginAccountDTO loginUserDto) {
        Account authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }
}