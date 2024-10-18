package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "subject")
//    private List<TutoringOffer> tutoringOffers;

    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }

//    public List<TutoringOffer> getTutoringOffers() {
//        return tutoringOffers;
//    }
//
//    public void setTutoringOffers(List<TutoringOffer> tutoringOffers) {
//        this.tutoringOffers = tutoringOffers;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
