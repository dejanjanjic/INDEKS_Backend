package net.etfbl.indeks.dto;

public class AddScheduleItemDTO {

    private int day;
    private String time;
    private Long scheduleId;

    private String content;

    public AddScheduleItemDTO() {}

    public AddScheduleItemDTO(int day, String time, Long scheduleId,String content)
    {

        this.day = day;
        this.time = time;
        this.scheduleId = scheduleId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
