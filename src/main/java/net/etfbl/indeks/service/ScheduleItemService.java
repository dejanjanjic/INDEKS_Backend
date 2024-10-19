package net.etfbl.indeks.service;

import net.etfbl.indeks.dto.AddScheduleItemDTO;
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

    public void addNewScheduleItem(AddScheduleItemDTO addScheduleItemDTO) {
        ScheduleItem scheduleItem = new ScheduleItem();
        scheduleItem.setDay(addScheduleItemDTO.getDay());
        scheduleItem.setTime(addScheduleItemDTO.getTime());

        Optional<Schedule> schedule = scheduleRepository.findById(addScheduleItemDTO.getScheduleId());
        if (schedule.isPresent()) {
            scheduleItem.setSchedule(schedule.get());
            scheduleItemRepository.save(scheduleItem);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule with ID " + addScheduleItemDTO.getScheduleId() + " not found");
        }
    }

    @Transactional
    public boolean updateScheduleItem(Long scheduleItemId, AddScheduleItemDTO addScheduleItemDTO) {
        Optional<ScheduleItem> scheduleItemOptional = scheduleItemRepository.findById(scheduleItemId);
        if (scheduleItemOptional.isEmpty()) {
            return false;
        }
        ScheduleItem scheduleItem = scheduleItemOptional.get();
        scheduleItem.setDay(addScheduleItemDTO.getDay());
        scheduleItem.setTime(addScheduleItemDTO.getTime());
        scheduleItem.setSchedule(new Schedule(addScheduleItemDTO.getScheduleId())); // samo ako je potrebno
        return true;
    }

}
