package nl.novi.backendeindopdracht.dtos.group;


import jakarta.validation.constraints.Min;

public class GroupInputDto {

    public String groupName;
    public String groupPlace;
    @Min(value=1)
    public Long[] relativeIds;


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
