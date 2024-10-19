package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class PrivateGroupChatMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupid")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "accountid")
    private PrivateGroupChat privateGroupChat;


    public PrivateGroupChatMember(){}

    public PrivateGroupChatMember(UserAccount userAccount, PrivateGroupChat privateGroupChat) {
        this.userAccount = userAccount;
        this.privateGroupChat = privateGroupChat;
    }

    public PrivateGroupChatMember(Long id, UserAccount userAccount, PrivateGroupChat privateGroupChat) {
        this.id = id;
        this.userAccount = userAccount;
        this.privateGroupChat = privateGroupChat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public PrivateGroupChat getPrivateGroupChat() {
        return privateGroupChat;
    }

    public void setPrivateGroupChat(PrivateGroupChat privateGroupChat) {
        this.privateGroupChat = privateGroupChat;
    }
}
