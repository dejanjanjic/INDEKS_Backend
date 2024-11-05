package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Schedule;
import net.etfbl.indeks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping(path = "{scheduleId}")
    public ResponseEntity<Optional<Schedule>> getSchedule(@PathVariable("scheduleId") Long scheduleId) {
        Optional<Schedule> schedule = scheduleService.getSchedule(scheduleId);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        scheduleService.addNewSchedule(schedule);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        boolean deleted = scheduleService.deleteSchedule(scheduleId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping
//    public ResponseEntity<Void> updateSchedule(@RequestBody Schedule schedule) {
//        boolean updated = scheduleService.updateSchedule(schedule.getId(), schedule.getContent());
//        if (updated) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
