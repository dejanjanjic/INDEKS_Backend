package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void addNewAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {

    }
}
