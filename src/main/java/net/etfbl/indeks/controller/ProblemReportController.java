package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.ProblemReport;
import net.etfbl.indeks.service.ProblemReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/problemReport")
public class ProblemReportController {

    private final ProblemReportService problemReportService;

    @Autowired
    public ProblemReportController(ProblemReportService problemReportService) {
        this.problemReportService = problemReportService;
    }

    @GetMapping
    public ResponseEntity<List<ProblemReport>> getSingleChats() {
        return ResponseEntity.ok(problemReportService.getProblemReports());
    }

    @GetMapping(path = "{problemReportId}")
    public ResponseEntity<ProblemReport> getSingleChatById(@PathVariable("problemReportId") Long id) {
        Optional<ProblemReport> problemReport = problemReportService.getProblemReportById(id);
        return problemReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProblemReport> addNewMessage(@RequestBody ProblemReport problemReport) {
        problemReportService.addNewProblemReport(problemReport);
        return new ResponseEntity<>(problemReport, HttpStatus.OK);
    }

    @DeleteMapping(path = "{problemReportId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("problemReportId") Long id) {
        boolean deleted = problemReportService.deleteProblemReport(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
