package net.etfbl.indeks.dto;

import java.time.LocalDateTime;

public class ProblemReportDetailsDTO {
    private Long id;
    private String reason;
    private LocalDateTime time;
    private Integer type;
    private String reporterName;
    private String reporterSurname;
    private String materialName;
    private String reportedName;
    private String reportedSurname;
    private String reviewText;

    // Getters and setters
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

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterSurname() {
        return reporterSurname;
    }

    public void setReporterSurname(String reporterSurname) {
        this.reporterSurname = reporterSurname;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getReportedName() {
        return reportedName;
    }

    public void setReportedName(String reportedName) {
        this.reportedName = reportedName;
    }

    public String getReportedSurname() {
        return reportedSurname;
    }

    public void setReportedSurname(String reportedSurname) {
        this.reportedSurname = reportedSurname;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
