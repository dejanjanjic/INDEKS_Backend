package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {
    List<ScheduleItem> findByScheduleId(Long scheduleId);
}
