package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Subject;
import net.etfbl.indeks.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable(name = "id") Long id) {
        Optional<Subject> subject = subjectService.getSubject(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/year/{year}")
    public ResponseEntity<List<Subject>> getSubjectsByYear(@PathVariable int year) {
        return ResponseEntity.ok(subjectService.getSubjectsByYear(year));
    }

    @PostMapping
    public ResponseEntity<Subject> registerNewSubject(@RequestBody Subject subject) {
        Subject temp = subjectService.addNewSubject(subject);
        if (temp != null) {
            return ResponseEntity.ok(temp);
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @DeleteMapping(path = "{accountId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("accountId") Long id) {
        boolean deleted = subjectService.deleteSubject(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateSubject(@RequestBody Subject subject) {
        boolean updated = subjectService.updateSubject(subject);
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
