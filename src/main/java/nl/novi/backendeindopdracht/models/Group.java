package nl.novi.backendeindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Set<Relative> relatives = new HashSet<>();

    // CONSTRUCTORS

    public Group() {
    }

    public Group(Long id, String groupName, String groupPlace, Set<Relative> relatives) {
        this.id = id;
        this.groupName = groupName;
        this.groupPlace = groupPlace;
        this.relatives = relatives;
    }

    // GETTERS AND SETTERS

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

    public Set<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(Set<Relative> relatives) {
        this.relatives = relatives;
    }

}
