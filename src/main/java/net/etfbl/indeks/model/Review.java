package net.etfbl.indeks.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table()
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "tutoringOfferId")
    @JsonBackReference
    private TutoringOffer tutoringOffer;

//    @ManyToOne
//    @JoinColumn(name = "id")
//    private StudentAccount studentAccount;

    public Review() {}

    public Review(String comment, LocalDateTime dateTime, TutoringOffer tutoringOffer) {
        this.comment = comment;
        this.dateTime = dateTime;
        this.tutoringOffer = tutoringOffer;
        //this.studentAccount = studentAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TutoringOffer getTutoringOffer() {
        return tutoringOffer;
    }

    public void setTutoringOffer(TutoringOffer tutoringOffer) {
        this.tutoringOffer = tutoringOffer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", dateTime=" + dateTime +
                ", tutoringOffer=" + tutoringOffer +
                ", studentAccount="+// studentAccount +
                '}';
    }
}
