package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class StudentAccount {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private UserAccount userAccount;

    @OneToOne
    @MapsId
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public StudentAccount(){

    }

    public StudentAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        this.id = userAccount.getAccountId();
    }

    public UserAccount getUserAccount(){ return userAccount;}

    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        this.id = userAccount.getAccountId();
    }

    public Long getId(){ return id;}
}
