package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPA")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupaID")
    private Long id;


    @Column(name = "nazivGrupe")
    private String groupName;


    public Group() {
    }


    public Group(Long grupaId, String groupName) {
        this.id = grupaId;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
