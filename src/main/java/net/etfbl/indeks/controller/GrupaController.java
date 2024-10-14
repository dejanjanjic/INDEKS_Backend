package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.GRUPA;
import net.etfbl.indeks.service.GrupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GrupaController
{
    @RestController
    @RequestMapping(path = "api/v1/grupa")
    public class StudentController {
        private final GrupaService grupaService;

        @Autowired
        public StudentController(GrupaService grupaService) {
            this.grupaService = grupaService;
        }

        @GetMapping
        public List<GRUPA> getStudents() {
            return grupaService.getGroups();
        }

//        @PostMapping
//        public void registerNewStudent(@RequestBody Student student){
//            studentService.addNewStudent(student);
//        }
//
//        @DeleteMapping(path = "{studentId}")
//        public void deleteStudent(@PathVariable("studentId")Long studentId){
//            studentService.deleteStudent(studentId);
//        }
//
//        @PutMapping(path="{studentId}")
//        public void updateStudent(
//                @PathVariable("studentId") Long studentId,
//                @RequestParam(required = false) String name,
//                @RequestParam(required = false) String email){
//            studentService.updateStudent(studentId, name, email);
//        }
    }
}
