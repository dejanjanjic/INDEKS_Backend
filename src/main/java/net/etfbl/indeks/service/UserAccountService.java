package net.etfbl.indeks.service;

import net.etfbl.indeks.dto.AddUserAccountDTO;
import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.UserAccount;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, AccountRepository accountRepository){
        this.userAccountRepository = userAccountRepository;
        this.accountRepository = accountRepository;
    }

    public List<UserAccount> getUserAccounts(){
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> getUserAccountById(Long id){
        return userAccountRepository.findById(id);
    }

    @Transactional
    public UserAccount addNewUserAccount(AddUserAccountDTO addUserAccountDTO){
        Optional<Account> accountByEmail = accountRepository.findByEmail(addUserAccountDTO.getEmail());
        if(accountByEmail.isEmpty()){
            Account account = new Account(addUserAccountDTO.getEmail(), addUserAccountDTO.getPassword());
            accountRepository.save(account);
            UserAccount userAccount = new UserAccount(addUserAccountDTO.getFirstName(), addUserAccountDTO.getLastName(), true, false, account);
            userAccountRepository.save(userAccount);
            return userAccount;
        }
        return null;
    }
}
