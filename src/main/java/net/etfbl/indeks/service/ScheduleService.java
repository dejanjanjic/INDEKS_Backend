package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Schedule;
import net.etfbl.indeks.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getSchedule(Long id) {
        return scheduleRepository.findById(id);
    }

    public void addNewSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public boolean deleteSchedule(Long id) {
        boolean exists = scheduleRepository.existsById(id);
        if (!exists) {
            return false;
        }
        scheduleRepository.deleteById(id);
        return true;
    }

//    @Transactional
//    public boolean updateSchedule(Long scheduleId, String content) {
//        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
//        if (schedule.isEmpty()) {
//            return false;
//        }
//        schedule.get().setContent(content);
//        return true;
//    }
}
