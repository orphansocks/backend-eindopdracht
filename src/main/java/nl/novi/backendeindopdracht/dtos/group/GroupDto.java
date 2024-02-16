package nl.novi.backendeindopdracht.dtos.group;

import nl.novi.backendeindopdracht.model.Relative;

import java.util.List;

public class GroupDto {

    public Long id;
    public String groupName;
    public String groupPlace;
    public List<Relative> relatives;



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

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

}
