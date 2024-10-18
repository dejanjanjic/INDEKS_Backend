package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class Schedule {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;

    public Schedule() {}

    public Schedule(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Schedule(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
