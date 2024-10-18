package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Schedule;
import net.etfbl.indeks.model.ScheduleItem;
import net.etfbl.indeks.repository.ScheduleItemRepository;
import net.etfbl.indeks.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleItemService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    public final ScheduleItemRepository scheduleItemRepository;

    @Autowired
    public ScheduleItemService(ScheduleItemRepository scheduleItemRepository) {
        this.scheduleItemRepository = scheduleItemRepository;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItemRepository.findAll();
    }

    public Optional<ScheduleItem> getScheduleItem(Long id) {
        return scheduleItemRepository.findById(id);
    }

    public boolean deleteScheduleItem(Long id) {
        boolean exists = scheduleItemRepository.existsById(id);
        if (!exists) {
            return false;
        }
        scheduleItemRepository.deleteById(id);
        return true;
    }

    public void addNewScheduleItem(ScheduleItem scheduleItem) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleItem.getScheduleId());
        if (schedule.isPresent()) {
            scheduleItemRepository.save(scheduleItem);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule with ID " + scheduleItem.getScheduleId() + " not found");
        }
    }

    @Transactional
    public boolean updateScheduleItem(Long scheduleItemId, int day, String time) {
        Optional<ScheduleItem> scheduleItem = scheduleItemRepository.findById(scheduleItemId);
        if (scheduleItem.isEmpty()) {
            return false;
        }
        scheduleItem.get().setDay(day);
        scheduleItem.get().setTime(time);
        return true;
    }

}
