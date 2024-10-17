package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class UserAccount {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private Account account;

    public UserAccount() {
    }

    public UserAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", account=" + account +
                '}';
    }
}
