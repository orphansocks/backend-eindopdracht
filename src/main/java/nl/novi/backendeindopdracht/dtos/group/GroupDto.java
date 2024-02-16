package nl.novi.backendeindopdracht.dtos.group;

import nl.novi.backendeindopdracht.model.Relative;

import java.util.List;

public class GroupDto {

    public Long id;
    public String groupName;
    public String groupPlace;
    public Long[] relativeIds;



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

    public Long[] getRelativeIds() {
        return relativeIds;
    }

    public void setRelativeIds(Long[] relativeIds) {
        this.relativeIds = relativeIds;
    }
}
