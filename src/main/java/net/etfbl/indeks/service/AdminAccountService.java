package net.etfbl.indeks.service;

import net.etfbl.indeks.dto.AddUserAccountDTO;
import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.AdminAccount;
import net.etfbl.indeks.model.UserAccount;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.repository.AdminACcountRepository;
import net.etfbl.indeks.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminAccountService {
    private final AdminACcountRepository adminAccountRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public AdminAccountService(AdminACcountRepository adminAccountRepository, AccountRepository accountRepository){
        this.adminAccountRepository = adminAccountRepository;
        this.accountRepository = accountRepository;
    }
    public List<AdminAccount> getAdminAccounts(){
        return adminAccountRepository.findAll();
    }

    public Optional<AdminAccount> getAdminAccountById(Long id){
        return adminAccountRepository.findById(id);
    }

    // ? postoji samo jedan admin ?
    // ? Ne trebaju metode za dodavanje, brisanje, update-ovanje ?
}
