package net.etfbl.indeks.model;

import jakarta.persistence.*;

@Entity
@Table
public class GroupChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;


    public GroupChat() {
    }


    public GroupChat(Long grupaId, String groupName) {
        this.id = grupaId;
        this.name = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupChat(String groupName) {
        this.name = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
