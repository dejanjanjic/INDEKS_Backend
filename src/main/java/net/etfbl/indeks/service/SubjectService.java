package net.etfbl.indeks.service;


import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.Subject;
import net.etfbl.indeks.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService
{
    private final SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }
    public Optional<Subject> getSubject(Long id) {
        return subjectRepository.findById(id);
    }
    public Subject addNewSubject(Subject subject) {subjectRepository.save(subject);
        return subject;
    }


    public boolean deleteSubject(Long id)
    {
        boolean exists = subjectRepository.existsById(id);
        if(!exists)
        {
           return false;
        }
        subjectRepository.deleteById(id);
        return true;
    }
    @Transactional
    public boolean updateSubject(Subject subject)
    {
        Optional<Subject> temp = subjectRepository.findById(subject.getId());
        if(temp.isEmpty())
        {
            return false;
        }
        temp.get().setName(subject.getName());
        return true;

    }
}
