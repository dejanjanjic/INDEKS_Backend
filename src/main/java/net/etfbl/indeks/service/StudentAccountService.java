package net.etfbl.indeks.service;

import net.etfbl.indeks.model.StudentAccount;
import net.etfbl.indeks.repository.StudentAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAccountService {

    private final StudentAccountRepository studentAccountRepository;

    @Autowired
    public StudentAccountService(StudentAccountRepository studentAccountRepository){
        this.studentAccountRepository = studentAccountRepository;
    }
    public List<StudentAccount> getStudentAccounts() {
        return studentAccountRepository.findAll();
    }

    public Optional<StudentAccount> getStudentAccountById(Long studentAccountId) {
        return studentAccountRepository.findById(studentAccountId);
    }
    public StudentAccount addNewStudentAccount(StudentAccount studentAccount) {
        Optional<StudentAccount> existingAccount = studentAccountRepository.findById(studentAccount.getId());
        if(existingAccount.isEmpty()){
            return studentAccountRepository.save(studentAccount);
        }
        return null;
    }
    public boolean deleteStudentAccount(Long studentAccountId) {
        boolean exists = studentAccountRepository.existsById(studentAccountId);
        if(!exists){
            return false;
        }
        studentAccountRepository.deleteById(studentAccountId);
        return true;
    }

    @Transactional
    public boolean updateStudentAccount(StudentAccount studentAccount) {
        Optional<StudentAccount> tempStudent = studentAccountRepository.findById(studentAccount.getId());
        if(tempStudent.isEmpty()){
            return false;
        }
        tempStudent.get().setSchedule(studentAccount.getSchedule());
        tempStudent.get().setUserAccount(studentAccount.getUserAccount());
        return true;
    }

}
