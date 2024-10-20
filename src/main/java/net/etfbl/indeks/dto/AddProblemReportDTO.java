package net.etfbl.indeks.dto;

import java.time.LocalDateTime;

public class AddProblemReportDTO {
    private Long id;
    private String reason;
    private LocalDateTime time;
    private Integer type;

    public AddProblemReportDTO() {

    }

    public AddProblemReportDTO(Long id, String reason, LocalDateTime time, Integer type) {
        this.id = id;
        this.reason = reason;
        this.time = time;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
