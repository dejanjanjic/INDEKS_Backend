package net.etfbl.indeks.service;


import net.etfbl.indeks.model.GroupChat;
import net.etfbl.indeks.model.Subject;
import net.etfbl.indeks.repository.GroupRepository;
import net.etfbl.indeks.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubjectService {
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

    public void addNewSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        boolean exists = subjectRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("subject doesn't exist");
        }
        subjectRepository.deleteById(id);
    }

    @Transactional
    public void updateSubject(Long id, String name) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new IllegalStateException("subject doesn't exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(subject.getName(), name)) {
            subject.setName(name);
        }

    }
}
