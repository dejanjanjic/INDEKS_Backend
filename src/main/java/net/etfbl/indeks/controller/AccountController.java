package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping
    public void registerNewAccount(@RequestBody Account account){
        accountService.addNewAccount(account);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId")Long accountId){
        accountService.deleteAccount(accountId);
    }
}
