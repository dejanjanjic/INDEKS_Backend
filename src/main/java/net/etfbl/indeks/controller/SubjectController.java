package net.etfbl.indeks.controller;


import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.GroupChat;
import net.etfbl.indeks.model.Subject;
import net.etfbl.indeks.service.AccountService;
import net.etfbl.indeks.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/subject")
public class SubjectController
{
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects(){
        return subjectService.getSubjects();
    }

    @GetMapping(path = "{id}")
    public Optional<Subject> getSubject(@PathVariable("id")Long id) {
        return subjectService.getSubject(id);
    }
    @PostMapping
    public void registerNewSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSubject(@PathVariable("id")Long id){
        subjectService.deleteSubject(id);
    }

    @PutMapping(path="{id}")
    public void updateSubject(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name){
        subjectService.updateSubject(id, name);
    }
}
