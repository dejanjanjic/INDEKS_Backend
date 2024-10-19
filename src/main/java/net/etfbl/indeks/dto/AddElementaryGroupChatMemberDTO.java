package net.etfbl.indeks.dto;

public class AddElementaryGroupChatMemberDTO {
    private Long id;
    private Long elementaryGroupChatId;
    private Long studentAccountId;

    public AddElementaryGroupChatMemberDTO() {
    }

    public AddElementaryGroupChatMemberDTO(Long id, Long elementaryGroupChatId, Long studentAccountId) {
        this.id = id;
        this.elementaryGroupChatId = elementaryGroupChatId;
        this.studentAccountId = studentAccountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElementaryGroupChatId() {
        return elementaryGroupChatId;
    }

    public void setElementaryGroupChatId(Long elementaryGroupChatId) {
        this.elementaryGroupChatId = elementaryGroupChatId;
    }

    public Long getStudentAccountId() {
        return studentAccountId;
    }

    public void setStudentAccountId(Long studentAccountId) {
        this.studentAccountId = studentAccountId;
    }

}
