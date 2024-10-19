package net.etfbl.indeks.dto;


public class AddPrivateGroupChatMemberDTO {
    private Long id;
    private Long privateGroupChatId;
    private Long studentAccountId;

    public AddPrivateGroupChatMemberDTO() {
    }

    public AddPrivateGroupChatMemberDTO(Long id, Long privateGroupChatId, Long studentAccountId) {
        this.id = id;
        this.privateGroupChatId = privateGroupChatId;
        this.studentAccountId = studentAccountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gePrivateGroupChatId() {
        return privateGroupChatId;
    }

    public void setPrivateGroupChatId(Long privateGroupChatId) {
        this.privateGroupChatId = privateGroupChatId;
    }

    public Long getStudentAccountId() {
        return studentAccountId;
    }

    public void setStudentAccountId(Long studentAccountId) {
        this.studentAccountId = studentAccountId;
    }

}
