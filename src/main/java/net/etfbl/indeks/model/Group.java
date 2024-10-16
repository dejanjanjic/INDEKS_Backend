package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPA")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupaID")
    private Long Id;


    @Column(name = "nazivGrupe")
    private String groupName;


    public Group() {
    }


    public Group(Long grupaId, String groupName) {
        this.Id = grupaId;
        this.groupName = groupName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
