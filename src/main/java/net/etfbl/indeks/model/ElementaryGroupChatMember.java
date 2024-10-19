package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class ElementaryGroupChatMember {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private ElementaryGroupChat elementaryGroupChat;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private StudentAccount studentAccount;

    public ElementaryGroupChatMember() {
    }

    public ElementaryGroupChatMember(Long id, ElementaryGroupChat elementaryGroupChat, StudentAccount studentAccount) {
        this.id = id;
        this.elementaryGroupChat = elementaryGroupChat;
        this.studentAccount = studentAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElementaryGroupChat getElementaryGroupChat() {
        return elementaryGroupChat;
    }

    public void setElementaryGroupChat(ElementaryGroupChat elementaryGroupChat) {
        this.elementaryGroupChat = elementaryGroupChat;
    }

    public StudentAccount getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }
}
