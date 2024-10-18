package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.StudentAccount;
import net.etfbl.indeks.service.StudentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/studentAccount")
public class StudentAccountController {

    private final StudentAccountService studentAccountService;

    @Autowired
    public StudentAccountController(StudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    @GetMapping
    public ResponseEntity<List<StudentAccount>> getAccounts() {
        return ResponseEntity.ok(studentAccountService.getStudentAccounts());
    }

    @GetMapping(path = "{accountId}")
    public ResponseEntity<StudentAccount> getStudentAccountById(@PathVariable(name = "accountId") Long accountId) {
        Optional<StudentAccount> studentAccount = studentAccountService.getStudentAccountById(accountId);
        if (studentAccount.isPresent()) {
            return ResponseEntity.ok(studentAccount.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
