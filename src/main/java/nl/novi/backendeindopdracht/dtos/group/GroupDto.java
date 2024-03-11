package nl.novi.backendeindopdracht.dtos.group;

import nl.novi.backendeindopdracht.models.Relative;

import java.util.Set;

public class GroupDto {

    public Long id;
    public String groupName;
    public String groupPlace;

    public Set<Relative> relatives;




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
