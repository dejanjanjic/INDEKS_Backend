package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.util.Encryption;
import net.etfbl.indeks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private Encryption encryption = new Encryption();

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable(name = "accountId")Long accountId){
        Optional<Account> account = accountService.getAccountById(accountId);
        if(account.isPresent()){
            return ResponseEntity.ok(account.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Account> registerNewAccount(@RequestBody Account account){
        account.setPassword(encryption.encryptPassword(account.getPassword()));
        Account temp = accountService.addNewAccount(account);
        if(temp != null){
            return ResponseEntity.ok(temp);
        }else{
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @DeleteMapping(path = "{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId")Long accountId){
        boolean deleted = accountService.deleteAccount(accountId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateAccount(@RequestBody Account account){
        account.setPassword(encryption.encryptPassword(account.getPassword()));
        boolean updated = accountService.updateAccount(account);
        if(updated){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
