package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
