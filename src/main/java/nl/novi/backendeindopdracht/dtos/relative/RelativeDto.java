package nl.novi.backendeindopdracht.dtos.relative;

import nl.novi.backendeindopdracht.dtos.group.GroupDto;
import nl.novi.backendeindopdracht.models.Group;
import nl.novi.backendeindopdracht.models.Relative;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class RelativeDto {

    public Long id;
   public String firstName;
    public String lastName;
    public String nickName;
    public LocalDate dob;
    public String socialStatus;
    public String nameOfPartner;
    public Boolean hasKids;
    public Integer amountOfKids;
    public String namesOfKids;
    public String misc;
    public String relation;
    private Set<Group> groups;


    public RelativeDto() {
    }

    public RelativeDto(Long id, String firstName, String lastName, String nickName, LocalDate dob, String socialStatus, String nameOfPartner, Boolean hasKids, Integer amountOfKids, String namesOfKids, String misc, String relation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dob = dob;
        this.socialStatus = socialStatus;
        this.nameOfPartner = nameOfPartner;
        this.hasKids = hasKids;
        this.amountOfKids = amountOfKids;
        this.namesOfKids = namesOfKids;
        this.misc = misc;
        this.relation = relation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getNameOfPartner() {
        return nameOfPartner;
    }

    public void setNameOfPartner(String nameOfPartner) {
        this.nameOfPartner = nameOfPartner;
    }

    public Boolean getHasKids() {
        return hasKids;
    }

    public void setHasKids(Boolean hasKids) {
        this.hasKids = hasKids;
    }

    public Integer getAmountOfKids() {
        return amountOfKids;
    }

    public void setAmountOfKids(Integer amountOfKids) {
        this.amountOfKids = amountOfKids;
    }

    public String getNamesOfKids() {
        return namesOfKids;
    }

    public void setNamesOfKids(String namesOfKids) {
        this.namesOfKids = namesOfKids;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}


