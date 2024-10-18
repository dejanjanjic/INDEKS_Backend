package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class ScheduleItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @JoinColumn(name = "id")
    private Long id;
    private int day;
    private Long scheduleId;
    private String time;

    public ScheduleItem() {
    }

    public ScheduleItem(Long id, int day, String time, Long scheduleId) {
        this.id = id;
        this.day = day;
        this.time = time;
        this.scheduleId = scheduleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
