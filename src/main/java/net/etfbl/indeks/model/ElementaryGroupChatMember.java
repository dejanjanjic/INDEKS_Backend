package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class ElementaryGroupChatMember {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @JoinColumn(name = "id")
    private Long id;
    private int groupId;
    private int accountId;

    public ElementaryGroupChatMember() {
    }

    public ElementaryGroupChatMember(Long id, int groupId, int accountId) {
        this.id = id;
        this.groupId = groupId;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
