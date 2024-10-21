package net.etfbl.indeks.dto;

public class UpdateProblemReportDTO {

    private Long id;
    private String reason;
    private Integer type;

    public UpdateProblemReportDTO() {

    }

    public UpdateProblemReportDTO(Long id, String reason, Integer type) {
        this.id = id;
        this.reason = reason;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
