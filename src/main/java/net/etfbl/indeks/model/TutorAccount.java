package net.etfbl.indeks.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class TutorAccount {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "tutorAccount")
    @JsonManagedReference
    private List<TutoringOffer> tutoringOffers;

    public TutorAccount(){

    }

    public TutorAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        //this.id = userAccount.getAccountId();
    }


    public UserAccount getUserAccount(){ return userAccount;}

    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
        //this.id = userAccount.getAccountId();
    }

    public Long getId(){ return id;}
}
