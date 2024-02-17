package nl.novi.backendeindopdracht.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String groupPlace;

    @ManyToMany
//    @JsonIgnore
    private Set<Relative> groupRelatives = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPlace() {
        return groupPlace;
    }

    public void setGroupPlace(String groupPlace) {
        this.groupPlace = groupPlace;
    }

    public Set<Relative> getGroupRelatives() {
        return groupRelatives;
    }

    public void setGroupRelatives(Set<Relative> groupRelatives) {
        this.groupRelatives = groupRelatives;
    }
}
