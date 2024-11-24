package net.etfbl.indeks.security.service;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.security.dto.LoginAccountDTO;
import net.etfbl.indeks.security.dto.RegisterAccountDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            AccountRepository accountRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account signup(RegisterAccountDTO input) {
        Account user = new Account();
        user.setId(input.getId());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return accountRepository.save(user);
    }

    public Account authenticate(LoginAccountDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return accountRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}