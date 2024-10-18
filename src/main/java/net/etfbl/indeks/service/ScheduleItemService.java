package net.etfbl.indeks.service;

import net.etfbl.indeks.model.ScheduleItem;
import net.etfbl.indeks.repository.ScheduleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleItemService {
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

    public void deleteScheduleItem(Long id) {
        boolean exists = scheduleItemRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("schedule item does not exist");
        }
        scheduleItemRepository.deleteById(id);
    }

    public void addNewScheduleItem(ScheduleItem scheduleItem) {
        scheduleItemRepository.save(scheduleItem);
    }

    @Transactional
    public void updateScheduleItemTime(Long scheduleItemId, String time) {
        Optional<ScheduleItem> scheduleItem1 = scheduleItemRepository.findById(scheduleItemId);
        scheduleItem1.ifPresent(scheduleItem -> scheduleItem.setTime(time));
    }

    @Transactional
    public void updateScheduleItemDay(Long scheduleItemId, int day) {
        Optional<ScheduleItem> scheduleItem1 = scheduleItemRepository.findById(scheduleItemId);
        scheduleItem1.ifPresent(scheduleItem -> scheduleItem.setDay(day));
    }

}
