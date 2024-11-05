package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.AddUserAccountDTO;
import net.etfbl.indeks.util.Encryption;
import net.etfbl.indeks.model.UserAccount;
import net.etfbl.indeks.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/userAccount")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private Encryption encryption = new Encryption();

    @Autowired
    public UserAccountController(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getUserAccounts(){
        return ResponseEntity.ok(userAccountService.getUserAccounts());
    }

    @GetMapping(path = "{userAccountId}")
    public ResponseEntity<UserAccount> getUserAccountById(@PathVariable(name = "userAccountId")Long userAccountId){
        Optional<UserAccount> userAccount = userAccountService.getUserAccountById(userAccountId);
        if(userAccount.isPresent()){
            return ResponseEntity.ok(userAccount.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserAccount> registerNewUserAccount(@RequestBody AddUserAccountDTO addUserAccountDTO){
        addUserAccountDTO.setPassword(encryption.encryptPassword(addUserAccountDTO.getPassword()));
        UserAccount temp = userAccountService.addNewUserAccount(addUserAccountDTO);
        if(temp != null){
            return ResponseEntity.ok(temp);
        }else{
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @DeleteMapping(path = "{userAccountId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable("userAccountId")Long userAccountId){
        boolean deleted = userAccountService.deleteUserAccount(userAccountId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateUserAccount(@RequestBody UserAccount userAccount){
        boolean updated = userAccountService.updateUserAccount(userAccount);
        if(updated){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
