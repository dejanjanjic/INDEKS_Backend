package net.etfbl.indeks.service;

import net.etfbl.indeks.model.ProblemReport;
import net.etfbl.indeks.repository.ProblemReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemReportService {
    private final ProblemReportRepository problemReportRepository;

    @Autowired
    public ProblemReportService(ProblemReportRepository problemReportRepository) {
        this.problemReportRepository = problemReportRepository;
    }

    public List<ProblemReport> getProblemReports() {
        return problemReportRepository.findAll();
    }

    public Optional<ProblemReport> getProblemReportById(Long id) {
        return problemReportRepository.findById(id);
    }

    public void addNewProblemReport(ProblemReport problemReport) {
        problemReportRepository.save(problemReport);
    }

    public boolean deleteProblemReport(Long id) {
        boolean exists = problemReportRepository.existsById(id);
        if (exists) {
            problemReportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
