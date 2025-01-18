package net.etfbl.indeks.dto;

public class AddTutoringOfferDTO {

    private String description;
    private Long subjectId;
    private Long studentAccountId;

    public AddTutoringOfferDTO() {}

    public AddTutoringOfferDTO(String description, Long subjectId, Long studentAccountId) {
        this.description = description;
        this.subjectId = subjectId;
        this.studentAccountId = studentAccountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentAccountId() {
        return studentAccountId;
    }

    public void setStudentAccountId(Long studentAccountId) {
        this.studentAccountId = studentAccountId;
    }


    @Override
    public String toString() {
        return "TutoringOfferDTO{" +
                ", description='" + description + '\'' +
                ", subjectId=" + subjectId +
                ", studentAccountId=" + studentAccountId +
                '}';
    }
}
