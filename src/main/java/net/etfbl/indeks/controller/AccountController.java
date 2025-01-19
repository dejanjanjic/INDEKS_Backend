package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.UpdateAccountDTO;
import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.util.Encryption;
import net.etfbl.indeks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "accountId") Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") Long accountId) {
        boolean deleted = accountService.deleteAccount(accountId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/password")
    public ResponseEntity<?> changePassword(@RequestBody UpdateAccountDTO updateAccountDTO) {
        try {
            accountService.changePassword(updateAccountDTO);
            return ResponseEntity.ok(Map.of("message", "Password changed!"));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("error", "New password must be different from the old password!"));
        }
    }
}
