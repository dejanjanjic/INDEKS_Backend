package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.ProblemReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemReportRepository extends JpaRepository<ProblemReport, Long> {
}
