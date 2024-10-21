package net.etfbl.indeks.dto;

public class AddSingleChatDTO {
    private Long id;
    private Long firstParticipantId;
    private Long secondParticipantId;

    public AddSingleChatDTO() {

    }

    public AddSingleChatDTO(Long id, Long firstParticipantId, Long secondParticipantId) {
        this.id = id;
        this.firstParticipantId = firstParticipantId;
        this.secondParticipantId = secondParticipantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstParticipantId() {
        return firstParticipantId;
    }

    public void setFirstParticipantId(Long firstParticipantId) {
        this.firstParticipantId = firstParticipantId;
    }

    public Long getSecondParticipantId() {
        return secondParticipantId;
    }

    public void setSecondParticipantId(Long secondParticipantId) {
        this.secondParticipantId = secondParticipantId;
    }
}
