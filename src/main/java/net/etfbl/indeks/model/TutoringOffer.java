package net.etfbl.indeks.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table()
public class TutoringOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

//    @ManyToOne
//    @JoinColumn(name = "id")
//    private InstructorAccount instructorAccount;

    @OneToMany(mappedBy = "tutoringOffer" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;


    public TutoringOffer() {}

    public TutoringOffer(String description, Subject subject) {
        this.description = description;
        this.subject = subject;
        //this.instructorAccount = instructorAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "TutoringOffer{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", subject=" + subject +
                ", instructorAccount=" + //instructorAccount +
                '}';
    }
}

