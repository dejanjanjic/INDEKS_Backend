package net.etfbl.indeks.service;

import net.etfbl.indeks.dto.AddUserAccountDTO;
import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.util.Encryption;
import net.etfbl.indeks.model.UserAccount;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final EmailService emailService;

    private Encryption encryption = new Encryption();

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, AccountRepository accountRepository, EmailService emailService) {
        this.userAccountRepository = userAccountRepository;
        this.accountRepository = accountRepository;
        this.emailService = emailService;
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
            Account savedAccount = accountRepository.save(account);
            UserAccount userAccount = new UserAccount(addUserAccountDTO.getFirstName(), addUserAccountDTO.getLastName(), true, false, savedAccount);

            return userAccountRepository.save(userAccount);
        }
        return null;
    }

    public boolean deleteUserAccount(Long id){
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if(userAccount.isEmpty()){
            return false;
        }
        userAccountRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean updateUserAccount(UserAccount userAccount) {
        Optional<UserAccount> temp = userAccountRepository.findById(userAccount.getId());
        if(temp.isEmpty()){
            return false;
        }
        UserAccount updatedUserAccount = temp.get();
        if(userAccount.getFirstName() != null){
            updatedUserAccount.setFirstName(userAccount.getFirstName());
        }
        if(userAccount.getLastName() != null){
            updatedUserAccount.setLastName(userAccount.getLastName());
        }if(userAccount.getActive() != null){
            updatedUserAccount.setActive(userAccount.getActive());
        }if(userAccount.getSuspended() != null){
            updatedUserAccount.setSuspended(userAccount.getSuspended());
        }

        userAccountRepository.save(updatedUserAccount);
        return true;
    }

    public boolean sendPasswordRecoveryEmail(String email) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        if (accountOpt.isPresent())
        {

            Account account = accountOpt.get();

            String recoveryToken =  UUID.randomUUID().toString().substring(0,8);

            account.setRecoveryToken(recoveryToken);

            accountRepository.save(account);

            emailService.sendEmail(email, "Oporavka lozinke",
                    "Unesite ovaj kod za oporavku u aplikaciji: " + recoveryToken);

            return true;
        }
        return false;
    }

    @Transactional
    public boolean verifyRecoveryToken(String email, String token) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();

            if (account.getRecoveryToken() != null && account.getRecoveryToken().equals(token)) {
                return true;
            }
        }
        return false;
    }
    @Transactional
    public boolean updatePassword(String email, String newPassword) {
        Optional<Account> accountOpt = accountRepository.findByEmail(email);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();


            String encryptedPassword = encryption.encryptPassword(newPassword);
            account.setPassword(encryptedPassword);


            account.setRecoveryToken(null);

            accountRepository.save(account);
            return true;
        }
        return false;
    }
}
