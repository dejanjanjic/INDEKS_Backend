package net.etfbl.indeks.dto;

public class AddScheduleItemDTO {
    private Long id;
    private int day;
    private String time;
    private Long scheduleId;

    public AddScheduleItemDTO() {}

    public AddScheduleItemDTO(Long id, int day, String time, Long scheduleId) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }


}
