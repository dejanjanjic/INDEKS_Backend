package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class UserAccount {
    @Id
    private Long accountId;

    private String firstName;
    private String lastName;
    private Boolean active;
    private Boolean suspended;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "accountId",
            referencedColumnName = "id")
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long id) {
        this.accountId = id;
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

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + accountId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", suspended=" + suspended +
                ", account=" + account +
                '}';
    }
}
