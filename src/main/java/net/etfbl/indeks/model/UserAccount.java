package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class UserAccount {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private Boolean active;
    private Boolean suspended;

    private transient String recoveryToken;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    @MapsId
    private Account account;

    public UserAccount() {
    }

    public UserAccount(String firstName, String lastName, Boolean active, Boolean suspended) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.suspended = suspended;
    }

    public UserAccount(String firstName, String lastName, Boolean active, Boolean suspended, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.suspended = suspended;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getRecoveryToken() {
        return recoveryToken;
    }

    public void setRecoveryToken(String recoveryToken) {
        this.recoveryToken = recoveryToken;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", suspended=" + suspended +
                ", account=" + account +
                '}';
    }
}
