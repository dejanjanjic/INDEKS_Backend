package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.ScheduleItem;
import net.etfbl.indeks.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/scheduleItem")
public class ScheduleItemController {
    private final ScheduleItemService scheduleItemService;

    @Autowired
    public ScheduleItemController(ScheduleItemService scheduleItemService) {
        this.scheduleItemService = scheduleItemService;
    }

    @GetMapping(path = "{scheduleItemId}")
    public ResponseEntity<Optional<ScheduleItem>> getScheduleItem(@PathVariable("scheduleItemId") Long scheduleItemId) {
        Optional<ScheduleItem> scheduleItem = scheduleItemService.getScheduleItem(scheduleItemId);
        if (scheduleItem.isPresent()) {
            return new ResponseEntity<>(scheduleItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ScheduleItem>> getScheduleItems() {
        List<ScheduleItem> scheduleItems = scheduleItemService.getScheduleItems();
        return new ResponseEntity<>(scheduleItems, HttpStatus.OK);
    }

    @PostMapping
    public void addScheduleItem(@RequestBody ScheduleItem scheduleItem) {
        scheduleItemService.addNewScheduleItem(scheduleItem);
    }

    @DeleteMapping(path = "{scheduleItemId}")
    public ResponseEntity<Void> deleteScheduleItem(@PathVariable("scheduleItemId") Long scheduleItemId) {
        boolean deleted = scheduleItemService.deleteScheduleItem(scheduleItemId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateScheduleItem(@RequestBody ScheduleItem scheduleItem) {
        boolean updated = scheduleItemService.updateScheduleItem(scheduleItem.getId(), scheduleItem.getDay(), scheduleItem.getTime());
        if (updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}