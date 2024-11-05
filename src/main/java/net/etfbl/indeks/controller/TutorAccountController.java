package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.AddUserAccountDTO;
import net.etfbl.indeks.model.Encryption;
import net.etfbl.indeks.model.TutorAccount;
import net.etfbl.indeks.service.TutorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/tutorAccount")
public class TutorAccountController {

    private final TutorAccountService tutorAccountService;
    private Encryption encryption = new Encryption();

    @Autowired
    public TutorAccountController(TutorAccountService tutorAccountService) {
        this.tutorAccountService = tutorAccountService;
    }

    @GetMapping
    public ResponseEntity<List<TutorAccount>> getAccounts() {
        return ResponseEntity.ok(tutorAccountService.getTutorAccounts());
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<TutorAccount> getTutorAccountById(@PathVariable(name = "accountId") Long accountId) {
        Optional<TutorAccount> tutorAccount = tutorAccountService.getTutorAccountById(accountId);
        if (tutorAccount.isPresent()) {
            return ResponseEntity.ok(tutorAccount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TutorAccount> registerNewTutorAccount(@RequestBody AddUserAccountDTO addUserAccountDTO) {
        addUserAccountDTO.setPassword(encryption.encryptPassword(addUserAccountDTO.getPassword()));
        TutorAccount newAccount = tutorAccountService.addNewTutorAccount(addUserAccountDTO);
        if (newAccount != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping(path = "{accountId}")
    public ResponseEntity<Void> deleteTutorAccount(@PathVariable("accountId") Long accountId) {
        boolean deleted = tutorAccountService.deleteTutorAccount(accountId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
