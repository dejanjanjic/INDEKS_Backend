package net.etfbl.indeks.model;
import jakarta.persistence.*;


@Entity
@Table
public class GRUPA
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long grupaId;

    @Column(name = "nazivGrupe")
    private String groupName;

    public GRUPA(Long grupaId, String groupName) {
        this.grupaId = grupaId;
        this.groupName = groupName;
    }

    public GRUPA(String groupName) {
        this.groupName = groupName;
    }

    public Long getGrupaId() {
        return grupaId;
    }

    public void setGrupaId(Long grupaId) {
        this.grupaId = grupaId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
