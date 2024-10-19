package net.etfbl.indeks.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "single_chat_id", referencedColumnName = "id")
    private SingleChat singleChat;

    @ManyToOne
    @JoinColumn(name = "group_chat_id", referencedColumnName = "id")
    private GroupChat groupChat;

    private MessageStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccount userAccount;

    public Message() {

    }

    public Message(String text, SingleChat singleChat, GroupChat groupChat,
                   MessageStatus status, UserAccount userAccount) {
        this.text = text;
        this.time = LocalDateTime.now();
        this.singleChat = singleChat;
        this.groupChat = groupChat;
        this.status = status;
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public SingleChat getSingleChat() {
        return singleChat;
    }

    public void setSingleChat(SingleChat singleChat) {
        this.singleChat = singleChat;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
