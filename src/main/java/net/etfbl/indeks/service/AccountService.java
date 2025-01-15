package net.etfbl.indeks.service;

import net.etfbl.indeks.dto.UpdateAccountDTO;
import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final Encryption encryption = new Encryption();

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }
    public Optional<Account> getAccountByEMail(String eMail) {
        return accountRepository.findByEmail(eMail);
    }

    public Account addNewAccount(Account account) {
        Optional<Account> accountByEmail = accountRepository.findByEmail(account.getEmail());
        if(accountByEmail.isEmpty()){
            return accountRepository.save(account);
        }
        return null;
    }

    public boolean deleteAccount(Long accountId) {
        boolean exists = accountRepository.existsById(accountId);
        if(!exists){
            return false;
        }
        accountRepository.deleteById(accountId);
        return true;
    }


    @Transactional
    public boolean updateAccount(Account account) {
        Optional<Account> temp = accountRepository.findById(account.getId());
        if(temp.isEmpty()){
            return false;
        }
        Account updatedAccount = temp.get();
        if(account.getEmail() != null){
            updatedAccount.setEmail(account.getEmail());
        }
        if(account.getPassword() != null){
            updatedAccount.setPassword(account.getPassword());
        }
        accountRepository.save(updatedAccount);
        return true;
    }

    @Transactional
    public void changePassword(UpdateAccountDTO updateAccountDTO) {

        String password = encryption.encryptPassword(updateAccountDTO.getPassword());

        if(!password.isEmpty()) {
            Account account = accountRepository.findByEmail(updateAccountDTO.getEmail())
                    .orElseThrow(() -> new RuntimeException("Account not found!"));

            if (!password.equals(account.getPassword())) {
                account.setPassword(password);
            } else {
                throw new IllegalArgumentException("New password must be different!");
            }
            accountRepository.save(account);
        }
    }
}
